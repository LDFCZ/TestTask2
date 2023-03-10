package ru.nsu.ccfit.lopatkin.test.config;

import org.jooq.ExecuteContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import java.util.Objects;

public class ExceptionTranslator extends DefaultExecuteListener {
    @Override
    public void exception(ExecuteContext context) {
        SQLDialect dialect = context.configuration().dialect();
        SQLExceptionTranslator translator = new SQLErrorCodeSQLExceptionTranslator(Objects.requireNonNull(dialect.thirdParty().springDbName()));

        context.exception(translator.translate("Access database using jOOQ", context.sql(), Objects.requireNonNull(context.sqlException())));
    }
}
