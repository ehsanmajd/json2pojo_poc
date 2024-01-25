package com.bse.common.customAnnotator.annotators;

import com.bse.common.customAnnotator.models.RoutedPayload;
import com.bse.common.customAnnotator.models.UnknownPayload;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;


public class MyAnnotator extends AbstractAnnotator {

    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        System.out.println("MyAnnotator.propertyField: " + propertyName);
        super.propertyField(field, clazz, propertyName, propertyNode);

        if (propertyName.equals("payload") && clazz.name().equals("Foo")) {
            field.annotate(JsonTypeInfo.class)
                    .param("use", JsonTypeInfo.Id.NAME)
                    .param("property", "type")
                    .param("include", JsonTypeInfo.As.EXTERNAL_PROPERTY)
                    .param("defaultImpl", UnknownPayload.class);
            field.annotate(JsonSubTypes.class)
                    .paramArray("value")
                    .annotate(JsonSubTypes.Type.class)
                    .param("value", RoutedPayload.class)
                    .param("name", "ROUTED");
        }
    }
}