package com.ProjetoExtensao.Projeto.servicos;

import com.ProjetoExtensao.Projeto.entidades.*;
import com.ProjetoExtensao.Projeto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // Importante para transações

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioMedicoService {

    @Autowired
    private ProntuarioMedicoRepository prontuarioMedicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository; // Para buscar pacientes
    @Autowired
    private ProfissionalSaudeRepository profissionalSaudeRepository; // Para buscar profissionais
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PrescricaoRepository prescricaoRepository;
    @Autowired
    private ExameRepository exameRepository;
    @Autowired
    private InternacaoRepository internacaoRepository;
    @Autowired
    private VacinaRepository vacinaRepository;
    @Autowired
    private EventoSentinelaRepository eventoSentinelaRepository; // Para operar com eventos sentinela



    @Transactional // Garante que a operação seja atômica no BD
    public ProntuarioMedico criarProntuario(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrada para criar prontuário. ID: " + pacienteId));

        if (prontuarioMedicoRepository.findByPaciente(paciente).isPresent()) {
            throw new IllegalArgumentException("Paciente já possui um prontuário.");
        }

        ProntuarioMedico prontuario = new ProntuarioMedico(paciente);
        return prontuarioMedicoRepository.save(prontuario);
    }

    public Optional<ProntuarioMedico> buscarProntuarioPorId(Long id) {
        return prontuarioMedicoRepository.findById(id);
    }

    public Optional<ProntuarioMedico> buscarProntuarioPorPacienteId(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrada. ID: " + pacienteId));
        return prontuarioMedicoRepository.findByPaciente(paciente);
    }



    @Transactional
    public Consulta adicionarConsulta(Long prontuarioId, LocalDateTime dataHora, Long profissionalId,
                                      String tipo, String motivoConsulta, String diagnosticoCID10,
                                      String anotacoesProfissional, List<String> encaminhamentos) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));
        ProfissionalSaude profissional = profissionalSaudeRepository.findById(profissionalId)
                .orElseThrow(() -> new RuntimeException("Profissional de saúde não encontrado. ID: " + profissionalId));

        Consulta consulta = new Consulta(dataHora, profissional, tipo, motivoConsulta, diagnosticoCID10, anotacoesProfissional);
        consulta.setEncaminhamentos(encaminhamentos); // Adiciona os encaminhamentos
        prontuario.adicionarNovaConsulta(consulta); // Adiciona na lista do prontuário e seta a referência inversa

        return consultaRepository.save(consulta); // Salvando a consulta explicitamente
    }

    public List<Consulta> buscarConsultasPorProntuario(Long prontuarioId) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));
        // A lista já vem carregada se o fetch for EAGER ou se você a acessar dentro de uma transação
        return prontuario.getConsultas();
    }

    @Transactional
    public Prescricao adicionarPrescricao(Long prontuarioId, String medicamento, String dosagem,
                                          String frequencia, LocalDate dataInicio, LocalDate dataFim, String observacoes) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));

        Prescricao prescricao = new Prescricao(medicamento, dosagem, frequencia, dataInicio);
        prescricao.setDataFim(dataFim);
        prescricao.setObservacoes(observacoes);
        prontuario.adicionarPrescricao(prescricao);
        return prescricaoRepository.save(prescricao);
    }

    public List<Prescricao> buscarPrescricoesPorProntuario(Long prontuarioId) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));
        return prontuario.getPrescricoes();
    }


    @Transactional
    public Exame adicionarExame(Long prontuarioId, String nomeExame, LocalDate dataSolicitacao,
                                String laboratorio) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));

        Exame exame = new Exame(nomeExame, dataSolicitacao);
        exame.setLaboratorio(laboratorio);
        prontuario.adicionarExame(exame);
        return exameRepository.save(exame);
    }

    @Transactional
    public Exame vincularResultadoExame(Long exameId, LocalDate dataResultado, String resultado) {
        Exame exame = exameRepository.findById(exameId)
                .orElseThrow(() -> new RuntimeException("Exame não encontrado. ID: " + exameId));
        exame.vincularResultado(dataResultado, resultado);
        return exameRepository.save(exame);
    }

    public List<Exame> buscarExamesPorProntuario(Long prontuarioId) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));
        return prontuario.getExames();
    }


    @Transactional
    public Internacao adicionarInternacao(Long prontuarioId, LocalDate dataEntrada, LocalDate dataSaida,
                                          String motivoInternacao, String diagnosticoInternacao, String hospital) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));

        Internacao internacao = new Internacao(dataEntrada, dataSaida, motivoInternacao, diagnosticoInternacao);
        internacao.setHospital(hospital);
        prontuario.adicionarInternacao(internacao);
        return internacaoRepository.save(internacao);
    }

    public List<Internacao> buscarInternacoesPorProntuario(Long prontuarioId) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));
        return prontuario.getHistoricoInternacoes();
    }


    @Transactional
    public Vacina adicionarVacina(Long prontuarioId, String nomeVacina, LocalDate dataAplicacao,
                                  String lote, String viaAplicacao) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));

        Vacina vacina = new Vacina(nomeVacina, dataAplicacao);
        vacina.setLote(lote);
        vacina.setViaAplicacao(viaAplicacao);
        prontuario.adicionarVacina(vacina);
        return vacinaRepository.save(vacina);
    }

    public List<Vacina> buscarVacinasPorProntuario(Long prontuarioId) {
        ProntuarioMedico prontuario = prontuarioMedicoRepository.findById(prontuarioId)
                .orElseThrow(() -> new RuntimeException("Prontuário não encontrado. ID: " + prontuarioId));
        return prontuario.getHistoricoVacinacao();
    }


    @Transactional
    public EventoSentinela registrarEventoSentinela(Long pacienteId, String descricaoEvento, LocalDate dataOcorrencia, String observacoes) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrada para registrar evento sentinela. ID: " + pacienteId));

        EventoSentinela evento = new EventoSentinela(paciente, descricaoEvento, dataOcorrencia);
        evento.setObservacoes(observacoes);
        return eventoSentinelaRepository.save(evento);
    }

    public List<EventoSentinela> buscarEventosSentinelaPorPaciente(Long pacienteId) {
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrada. ID: " + pacienteId));
        return eventoSentinelaRepository.findByPaciente(paciente);
    }

    public List<EventoSentinela> buscarTodosEventosSentinela() {
        return eventoSentinelaRepository.findAll();
    }


}