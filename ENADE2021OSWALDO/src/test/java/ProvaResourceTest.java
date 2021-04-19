
import br.edu.uniacademia.enade.model.Prova;
import br.edu.uniacademia.enade.resources.ProvaResource;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oswaldo
 */
public class ProvaResourceTest {
   
    ProvaResource provaResource = mock(ProvaResource.class);
    List<Prova> arrayProva = new ArrayList<>();
    Prova prova = mock(Prova.class);
    
    public ProvaResourceTest() {
    }
    
    @Test
    public void testTodosProva() {
        arrayProva.add(prova);
        when(provaResource.TodasProvas()).thenReturn(arrayProva);
        assertEquals(arrayProva, provaResource.TodasProvas());
    }

    @Test
    public void testGetProva() {
        when(provaResource.GetProva(prova.getIdProva())).thenReturn(prova);
        assertEquals(prova, provaResource.GetProva(prova.getIdProva()));
    }

    @Test
    public void testAlterar() {
        String provaString = prova.toString();
        when(provaResource.Alterar(prova)).thenReturn(provaString);
        assertEquals(prova.toString(), provaResource.Alterar(prova));
    }

    @Test
    public void testExcluir() {
        when(provaResource.Excluir(prova.getIdProva())).thenReturn("Registro excluído com sucesso");
        assertEquals("Registro excluído com sucesso", provaResource.Excluir(prova.getIdProva()));
    }

    @Test
    public void testExcluirTodos() {
        when(provaResource.ExcluirTodas()).thenReturn("Todos registros foram excluídos");
        assertEquals("Todos registros foram excluídos", provaResource.ExcluirTodas());
    }
}
