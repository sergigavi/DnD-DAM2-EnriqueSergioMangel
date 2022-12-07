package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Alineamiento;

@ReadingConverter
public class StringToEnumConverterAlineamiento implements Converter<String,Alineamiento>{

    @Override
    public Alineamiento convert(String source) {
        return Alineamiento.getAlineamiento(source);
    }
    
}
