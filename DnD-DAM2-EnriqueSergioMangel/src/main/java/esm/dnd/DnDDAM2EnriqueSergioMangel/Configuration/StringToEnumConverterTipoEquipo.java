package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.TipoEquipo;

public class StringToEnumConverterTipoEquipo implements Converter<String,TipoEquipo> {

    @Override
    @Nullable
    public TipoEquipo convert(String source) {
        
        try {
        return TipoEquipo.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
}
