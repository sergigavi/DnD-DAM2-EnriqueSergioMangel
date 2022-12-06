package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.CatEquipo;

@ReadingConverter
public class StringToEnumConverterCatEquipo implements Converter<String,CatEquipo> {

    @Override
    public CatEquipo convert(String source) {
        
        return CatEquipo.getCatEquipo(source);
    }
    
}
