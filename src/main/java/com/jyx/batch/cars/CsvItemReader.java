package com.jyx.batch.cars;

import jakarta.batch.api.chunk.AbstractItemReader;
import jakarta.batch.api.chunk.ItemReader;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.Serializable;

@Named("csvItemReader")
@ApplicationScoped
public class CsvItemReader implements ItemReader {

    private CsvBeanReader reader;

    @ConfigProperty(name = "filePath")
    private String filePath;

    final CellProcessor[] processors = new CellProcessor[]{
            new Optional(new ParseInt()),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            new Optional(new ParseInt()),
            new Optional( new ParseInt()),
            null,
            null,
            null,
            new Optional (new ParseInt()),
            new Optional(new ParseInt()),
            null
    };


    @Override
    public void open(Serializable serializable) throws Exception {
        reader = new CsvBeanReader(new FileReader(filePath), CsvPreference.STANDARD_PREFERENCE);
        reader.getHeader(true); // skip the header
    }


    @Override
    public Object readItem() throws Exception {
        if (reader == null) return null;

        String[] headers = new String[]{
                "year", "make", "model", "trim", "body",
                "transmission", "vin", "state", "condition",
                "odometer", "color", "interior",
                "seller", "mmr", "sellingprice", "saledate"
        };
        return reader.read(VehicleDTO.class, headers, processors);
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
