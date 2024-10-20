package br.com.fiap.beautymanagerapi.configuracao;

import br.com.fiap.beautymanagerapi.adapters.gateways.agendamento.AgendamentoRepository;
import br.com.fiap.beautymanagerapi.adapters.gateways.agendamento.AgendamentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.avaliacao.AvaliacaoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.cliente.ClienteRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.endereco.EnderecoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.estabelecimento.EstabelecimentoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.localizacao.LocalizacaoRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.profissional.ProfissionalRepositoryImpl;
import br.com.fiap.beautymanagerapi.adapters.gateways.servico.ServicoRepositoryImpl;
import br.com.fiap.beautymanagerapi.usecase.agendamento.AtualizarAgendamentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.agendamento.BuscarAgendamentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.agendamento.CriarAgendamentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.avaliacao.BuscarAvaliacaoUseCase;
import br.com.fiap.beautymanagerapi.usecase.avaliacao.CriarAvaliacaoUseCase;
import br.com.fiap.beautymanagerapi.usecase.cliente.BuscarClienteUseCase;
import br.com.fiap.beautymanagerapi.usecase.cliente.CriarClienteUseCase;
import br.com.fiap.beautymanagerapi.usecase.endereco.CriarAtualizarEnderecoUseCase;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.BuscarEstabelecimentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.estabelecimento.CriarEstabelecimentoUseCase;
import br.com.fiap.beautymanagerapi.usecase.localizacao.BuscarEstabelecimentosProximosUseCase;
import br.com.fiap.beautymanagerapi.usecase.localizacao.CriarLocalizacaoUseCase;
import br.com.fiap.beautymanagerapi.usecase.profissional.BuscarProfissionalUseCase;
import br.com.fiap.beautymanagerapi.usecase.profissional.CriarProfissionalUseCase;
import br.com.fiap.beautymanagerapi.usecase.servico.BuscarServicoUseCase;
import br.com.fiap.beautymanagerapi.usecase.servico.CriarServicoUseCase;
import br.com.fiap.beautymanagerapi.usecase.servico.DeletarServicoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CriarClienteUseCase criarClienteUseCase(ClienteRepositoryImpl repository) {
        return new CriarClienteUseCase(repository);
    }

    @Bean
    public BuscarClienteUseCase buscarClienteUseCase(ClienteRepositoryImpl repository) {
        return new BuscarClienteUseCase(repository);
    }

    @Bean
    public CriarAvaliacaoUseCase criarAvaliacaoUseCase(AvaliacaoRepositoryImpl avaliacaoRepository, EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new CriarAvaliacaoUseCase(avaliacaoRepository, estabelecimentoRepository);
    }

    @Bean
    public BuscarAvaliacaoUseCase buscarAvaliacaoUseCase(AvaliacaoRepositoryImpl avaliacaoRepository) {
        return new BuscarAvaliacaoUseCase(avaliacaoRepository);
    }

    @Bean
    public CriarProfissionalUseCase criarProfissionalUseCase(ProfissionalRepositoryImpl profissionalRepository, EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new CriarProfissionalUseCase(profissionalRepository, estabelecimentoRepository);
    }

    @Bean
    public BuscarProfissionalUseCase buscarProfissionalUseCase(ProfissionalRepositoryImpl profissionalRepository) {
        return new BuscarProfissionalUseCase(profissionalRepository);
    }

    @Bean
    public CriarLocalizacaoUseCase criarLocalizacaoUseCase(LocalizacaoRepositoryImpl localizacaoRepository, EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new CriarLocalizacaoUseCase(localizacaoRepository, estabelecimentoRepository);
    }

    @Bean
    public BuscarEstabelecimentosProximosUseCase buscarEstabelecimentosProximosUseCase(LocalizacaoRepositoryImpl localizacaoRepository) {
        return new BuscarEstabelecimentosProximosUseCase(localizacaoRepository);
    }

    @Bean
    public CriarServicoUseCase criarServicoUseCase(ServicoRepositoryImpl servicoRepository
            , EstabelecimentoRepositoryImpl estabelecimentoRepository, ProfissionalRepositoryImpl profissionalRepository) {
        return new CriarServicoUseCase(servicoRepository, estabelecimentoRepository,profissionalRepository );
    }

    @Bean
    public BuscarServicoUseCase buscarServicoUseCase(ServicoRepositoryImpl servicoRepository) {
        return new BuscarServicoUseCase(servicoRepository );
    }

    @Bean
    public DeletarServicoUseCase deletarServicoUseCase(ServicoRepositoryImpl servicoRepository) {
        return new DeletarServicoUseCase(servicoRepository );
    }

    @Bean
    public CriarEstabelecimentoUseCase criarEstabelecimentoUseCase(EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new CriarEstabelecimentoUseCase(estabelecimentoRepository);
    }

    @Bean
    public BuscarEstabelecimentoUseCase buscarEstabelecimentoUseCase(EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new BuscarEstabelecimentoUseCase(estabelecimentoRepository);
    }

    @Bean
    public CriarAgendamentoUseCase criarAgendamentoUseCase(AgendamentoRepositoryImpl agendamentoRepository,
                                                           EstabelecimentoRepositoryImpl estabelecimentoRepository,
                                                           ProfissionalRepositoryImpl profissionalRepository,
                                                           ServicoRepositoryImpl servicoRepository,
                                                           ClienteRepositoryImpl clienteRepository) {
        return new CriarAgendamentoUseCase(agendamentoRepository, estabelecimentoRepository, profissionalRepository, servicoRepository, clienteRepository);
    }

    @Bean
    public BuscarAgendamentoUseCase buscarAgendamentoUseCase(AgendamentoRepositoryImpl agendamentoRepository,
                                                             EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new BuscarAgendamentoUseCase(agendamentoRepository, estabelecimentoRepository);
    }

    @Bean
    public AtualizarAgendamentoUseCase atualizarAgendamentoUseCase(AgendamentoRepository agendamentoRepository,
                                                                EstabelecimentoRepositoryImpl estabelecimentoRepository) {
        return new AtualizarAgendamentoUseCase(agendamentoRepository, estabelecimentoRepository);
    }

    @Bean
    public CriarAtualizarEnderecoUseCase CriarAtualizarEnderecoUseCase(EnderecoRepositoryImpl  enderecoRepository, EstabelecimentoRepositoryImpl estabelecimentoRepository){
        return new CriarAtualizarEnderecoUseCase(enderecoRepository,estabelecimentoRepository);
    }
}
