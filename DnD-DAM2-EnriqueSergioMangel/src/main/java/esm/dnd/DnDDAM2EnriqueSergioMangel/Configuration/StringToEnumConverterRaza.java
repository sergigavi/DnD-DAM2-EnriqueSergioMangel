package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.Raza;

@ReadingConverter
public class StringToEnumConverterRaza implements Converter<String,Raza> {

    @Override
    public Raza convert(String source) {
        return Raza.getCRaza(source);
    }
    
}
