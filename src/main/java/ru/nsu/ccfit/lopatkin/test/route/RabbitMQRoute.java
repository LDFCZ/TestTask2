package ru.nsu.ccfit.lopatkin.test.route;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;
import ru.nsu.ccfit.lopatkin.test.dto.RabbitDTO;
import ru.nsu.ccfit.lopatkin.test.service.DiscountService;

import static org.apache.camel.LoggingLevel.INFO;
import static ru.nsu.ccfit.lopatkin.test.config.CamelConfiguration.RABBIT_URI;

@Component
public class RabbitMQRoute extends RouteBuilder {

    private final DiscountService discountService;

    public RabbitMQRoute(DiscountService discountService) {
        this.discountService = discountService;
    }

    @Override
    public void configure() throws Exception {
        CamelContext context = new DefaultCamelContext();
        fromF(RABBIT_URI, "test", "test")
                .log(INFO, "Get message from rabbit: ${body}")
                .unmarshal().json(JsonLibrary.Jackson, RabbitDTO.class)
                .process(this::makeDiscount);
    }

    private void makeDiscount(Exchange exchange) {
        RabbitDTO rabbitDTO = exchange.getMessage().getBody(RabbitDTO.class);
        discountService.updateDiscount(rabbitDTO);
    }

}
