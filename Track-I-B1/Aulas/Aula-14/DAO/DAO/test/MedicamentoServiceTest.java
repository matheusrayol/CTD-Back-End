import medicamentos.dao.ConfiguracaoJdbc;
import medicamentos.dao.impl.MedicamentoDaoImpl;
import medicamentos.model.Medicamento;
import medicamentos.service.MedicamentoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MedicamentoServiceTest {

    private MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDaoImpl(new ConfiguracaoJdbc()));

    @Test
    public void salvarMedicamento() {
        Medicamento medicamento = new Medicamento("Ibuprofeno", "Ache", 395, 23.0);
        medicamentoService.salvar(medicamento);
        assertTrue(medicamento.getId() != null);
    }


}
