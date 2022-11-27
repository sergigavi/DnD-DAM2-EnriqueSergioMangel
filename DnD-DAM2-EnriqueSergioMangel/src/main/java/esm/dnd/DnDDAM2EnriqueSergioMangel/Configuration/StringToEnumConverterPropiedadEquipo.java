package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.PropiedadEquipo;

public class StringToEnumConverterPropiedadEquipo implements Converter<String,PropiedadEquipo> {

    @Override
    @Nullable
    public PropiedadEquipo convert(String source) {
        
        return PropiedadEquipo.valueOf(source.toUpperCase());
    }
    
}
