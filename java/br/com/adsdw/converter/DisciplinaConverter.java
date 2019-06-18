package br.com.adsdw.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.adsdw.model.Disciplina;
import br.com.adsdw.repository.Disciplinas;
import br.com.adsdw.util.cdi.CDIServiceLocator;

@FacesConverter(forClass = Disciplina.class)
public class DisciplinaConverter implements Converter {

	//@Inject
	private Disciplinas disciplinas;
	
	public DisciplinaConverter() {
		disciplinas = CDIServiceLocator.getBean(Disciplinas.class);
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Disciplina retorno = null;
		
		if (value != null) {
			Long id = new Long(value);
			retorno = disciplinas.porId(id);
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		try {
			if (value != null) {
				return ((Disciplina) value).getId().toString();
			}
		
		} catch (NullPointerException e) {
			System.out.print("erro: " + e);
		}
		return "";
	}

}
