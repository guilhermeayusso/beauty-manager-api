-- Deletar Fotos de Estabelecimentos
DELETE FROM ESTABELECIMENTO_ENTITY_FOTOS;

-- Deletar Agendamentos
DELETE FROM tb_agendamento;

-- Deletar Avaliações
DELETE FROM tb_avaliacao;

-- Deletar Localizações
DELETE FROM tb_localizacao;

-- Deletar Servico_Profissionais (relação many-to-many entre serviços e profissionais)
DELETE FROM servico_profissionais;

-- Deletar Serviços
DELETE FROM tb_servico;

-- Deletar Profissionais
DELETE FROM tb_profissional;

-- Deletar Endereços
DELETE FROM tb_endereco;

-- Deletar Estabelecimentos
DELETE FROM tb_estabelecimento;

-- Deletar Clientes
DELETE FROM tb_cliente;
