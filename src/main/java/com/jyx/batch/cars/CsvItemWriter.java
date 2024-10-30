package com.jyx.batch.cars;

import jakarta.batch.api.chunk.ItemWriter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import lombok.SneakyThrows;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
@Named("csvItemWriter")
public class CsvItemWriter implements ItemWriter {

    private CsvBeanWriter writer;

    @ConfigProperty(name = "outPath")
    private String output;

    private String[] headers =  new String[]{
        "year", "make", "model", "trim", "body",
                "transmission", "vin", "state", "condition",
                "odometer", "color", "interior",
                "seller", "mmr", "sellingprice", "saledate"
    };

    @Override
    public void open(Serializable checkpoint) throws Exception {
        writer = new CsvBeanWriter(new FileWriter(output), CsvPreference.STANDARD_PREFERENCE);
        writer.writeHeader(headers);
    }

    @Override
    @SneakyThrows
    public void writeItems(List<Object> items) throws Exception {


        items.stream().map(v -> (VehicleDTO) v).forEach(this::doWrite);
    }

    @Override
    public void close() throws Exception {
        if (writer != null) {
            writer.close();
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }

    /**
     *
     * @param dto
     */
    private void doWrite(final VehicleDTO dto) {

        try {

            writer.write(dto, headers);
        }

        catch (IOException e) {

            throw new RuntimeException(e);
        }
    }
}
