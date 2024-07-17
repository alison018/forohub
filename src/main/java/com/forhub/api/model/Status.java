package com.forhub.api.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

@JsonDeserialize(using = Status.StatusDeserializer.class)
public enum Status {
    OPEN("open"),
    CLOSED("closed");

    private final String nombre;

    Status(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public static Status fromString(String nombre) {
        for (Status status : Status.values()) {
            if (status.getNombre().equalsIgnoreCase(nombre)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + nombre);
    }

    public static class StatusDeserializer extends JsonDeserializer<Status> {
        @Override
        public Status deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String nombre = jsonParser.getText();
            return Status.fromString(nombre);
        }
    }
}
