package com.sagitta.taxservice.tax.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class NestedHashMapSerializer extends JsonSerializer<HashMap<Integer, HashMap<Double, Double>>> {

    @Override
    public void serialize(
        HashMap<Integer, HashMap<Double, Double>> value,
        JsonGenerator gen,
        SerializerProvider serializers
    ) throws IOException {
        // Custom serialization logic here
        gen.writeStartObject();
        for (Integer key : value.keySet()) {
            gen.writeFieldName(key.toString());
            gen.writeObject(value.get(key));
        }
        gen.writeEndObject();
    }
}

