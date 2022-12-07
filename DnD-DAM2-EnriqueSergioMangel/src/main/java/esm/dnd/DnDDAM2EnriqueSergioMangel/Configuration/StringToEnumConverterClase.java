package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Clase;

@ReadingConverter
public class StringToEnumConverterClase implements Converter<String,Clase> {

    @Override
    public Clase convert(String source) {

        return Clase.getClase(source);
    }
    
}
