package esm.dnd.DnDDAM2EnriqueSergioMangel.Configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

import esm.dnd.DnDDAM2EnriqueSergioMangel.modelo.TipoEquipo;

@ReadingConverter
public class StringToEnumConverterTipoEquipo implements Converter<String,TipoEquipo> {

    @Override
    public TipoEquipo convert(String source) {
        return TipoEquipo.getTipo(source);
    }
}
