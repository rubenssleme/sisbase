package br.laramara.sistelemarketingclient.utilitarios;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@SuppressWarnings("rawtypes")
@FacesConverter("conversorDeBooleano")
public class ConversorDeBooleano implements Converter {

    public ConversorDeBooleano() {
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String param) {
        try {
            Boolean result = Boolean.parseBoolean(param);
            return result;
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object obj) {
        try {
            if (obj != null && ((Boolean) obj)) {
                return "Sim";
            } else {
                return "Não";
            }
        } catch (Exception exception) {
            throw new ConverterException(exception);
        }
    }
}