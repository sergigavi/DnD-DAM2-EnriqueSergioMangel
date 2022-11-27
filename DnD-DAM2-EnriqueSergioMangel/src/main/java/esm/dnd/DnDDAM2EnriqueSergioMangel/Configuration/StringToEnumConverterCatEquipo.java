package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.CatEquipo;

public class StringToEnumConverterCatEquipo implements Converter<String,CatEquipo> {

    @Override
    @Nullable
    public CatEquipo convert(String source) {
        
        return CatEquipo.valueOf(source.toUpperCase());
    }
    
}
