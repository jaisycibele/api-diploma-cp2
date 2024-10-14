package br.com.api_diploma.service;

import br.com.api_diploma.dto.DiplomaRequest;
import br.com.api_diploma.dto.DiplomaResponse;
import br.com.api_diploma.model.Diploma;
import br.com.api_diploma.model.Diplomado;
import br.com.api_diploma.model.Curso;
import br.com.api_diploma.repository.CursoRepository;
import br.com.api_diploma.repository.DiplomadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.api_diploma.model.Sexo;


@Service
public class DiplomaService {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private DiplomadoRepository diplomadoRepository;

    public Diploma requestToDiploma(DiplomaRequest diplomaRequest) {
        Diploma diploma = new Diploma();

        Curso curso = cursoRepository.findById(diplomaRequest.getCurso().getId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Diplomado diplomado = diplomadoRepository.findById(diplomaRequest.getDiplomado().getId())
                .orElseGet(() -> diplomadoRepository.save(diplomaRequest.getDiplomado()));

        diploma.setDataConclusao(diplomaRequest.getDataConclusao());
        diploma.setSexo(diplomaRequest.getSexo());
        diploma.setNomeReitor(diplomaRequest.getNomeReitor());
        diploma.setDiplomado(diplomado);
        diploma.setCurso(curso);

        return diploma;
    }

    public DiplomaResponse diplomaToResponse(Diploma diploma) {
        DiplomaResponse diplomaResponse = new DiplomaResponse();
        Diplomado diplomado = diploma.getDiplomado();
        Curso curso = diploma.getCurso();

        diplomaResponse.setId(diploma.getId());
        diplomaResponse.setNomeDiplomado(diplomado.getNome());
        diplomaResponse.setNacionalidade(diplomado.getNacionalidade());
        diplomaResponse.setNaturalidade(diplomado.getNaturalidade());
        diplomaResponse.setNumeroRg(diplomado.getRg());
        diplomaResponse.setNomeCurso(curso.getNome());
        diplomaResponse.setTipoCurso(curso.getTipo().name());
        diplomaResponse.setDataConclusao(diploma.getDataConclusao());
        diplomaResponse.setNomeReitor(diploma.getNomeReitor());
        diplomaResponse.setSexoReitor(diploma.getSexo());

        return diplomaResponse;
    }

    public DiplomaRequest diplomaToRequest(Diploma diploma) {
        DiplomaRequest diplomaRequest = new DiplomaRequest();
        diplomaRequest.setDataConclusao(diploma.getDataConclusao());
        diplomaRequest.setSexo(diploma.getSexo());
        diplomaRequest.setNomeReitor(diploma.getNomeReitor());
        diplomaRequest.setDiplomado(diploma.getDiplomado());
        diplomaRequest.setCurso(diploma.getCurso());
        return diplomaRequest;
    }
    public String gerarTextoDiploma(DiplomaResponse diplomaResponse) {
        String tituloReitor = diplomaResponse.getSexoReitor() == Sexo.M ? "Prof. Dr." : "Profa. Dra.";
        String cargoReitor = diplomaResponse.getSexoReitor() == Sexo.M ? "reitor" : "reitora";

        return String.format(
                "%s %s, da Universidade Fake, no uso de suas atribuições, confere a %s, de nacionalidade %s, natural de %s, portador do rg %s, o presente diploma de %s, do curso de %s, por ter concluído seus estudos nesta instituição de ensino no dia %s.",
                tituloReitor, diplomaResponse.getNomeReitor(),
                diplomaResponse.getNomeDiplomado(), diplomaResponse.getNacionalidade(),
                diplomaResponse.getNaturalidade(), diplomaResponse.getNumeroRg(),
                diplomaResponse.getTipoCurso(), diplomaResponse.getNomeCurso(),
                diplomaResponse.getDataConclusao()
        );
    }


}
