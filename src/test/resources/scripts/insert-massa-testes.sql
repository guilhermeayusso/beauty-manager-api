-- Dados de Clientes
INSERT INTO tb_cliente (id,nome, email, telefone) VALUES
                                                   (1,'João Silva', 'joao.silva@example.com', '11987654321'),
                                                   (2,'Maria Oliveira', 'maria.oliveira@example.com', '21987654321'),
                                                   (3,'Carlos Almeida', 'carlos.almeida@example.com', '31987654321');

-- Dados de Estabelecimentos
INSERT INTO tb_estabelecimento (id,nome, horario_de_abertura, horario_de_fechamento, tipo_estabelecimento) VALUES
                                                                                                            (11,'Beleza & Estilo', '09:00:00', '18:00:00', 'SALAO_DE_BELEZA'),
                                                                                                            (22,'Corte Certo', '08:00:00', '20:00:00', 'BARBEARIA'),
                                                                                                            (33,'Spa Relax', '10:00:00', '22:00:00', 'SPA'),
                                                                                                            (44,'The Shaving', '08:00:00', '20:00:00', 'SALAO_DE_BELEZA');

-- Dados de Endereços
INSERT INTO tb_endereco (id,cep, logradouro, numero, complemento, bairro, cidade, uf, estabelecimento_id) VALUES
                                                                                                           (11,'12345-678', 'Rua das Flores', '123', 'Apto 101', 'Centro', 'Osasco', 'SP', 11),
                                                                                                           (22,'23456-789', 'Avenida Brasil', '456', '', 'Jardins', 'São Paulo', 'SP', 22),
                                                                                                           (33,'34567-890', 'Praça da Liberdade', '789', 'Sala 3', 'Liberdade', 'São Paulo', 'SP', 33);

-- Dados de Profissionais
INSERT INTO tb_profissional (id,nome, especialidades, status_profissional, estabelecimento_id) VALUES
                                                                                                (11,'Fernando Souza', 'Corte de Cabelo, Barba', 'DISPONIVEL', 11),
                                                                                                (22,'Ana Paula', 'Manicure, Pedicure', 'OCUPADO', 11),
                                                                                                (33,'Ricardo Lima', 'Massagem', 'DISPONIVEL', 33);

-- Dados de Serviços
INSERT INTO tb_servico (id,nome, descricao, preco, estabelecimento_id) VALUES
                                                                        (11,'Corte de Cabelo', 'Corte de cabelo masculino e feminino', 50.00, 11),
                                                                        (22,'Manicure', 'Serviço completo de manicure', 30.00, 11),
                                                                        (33,'Massagem Relaxante', 'Massagem para relaxamento', 100.00, 33);

-- Inserção de dados na tabela Localizacao (associando com Estabelecimentos)
INSERT INTO tb_localizacao (id,latitude, longitude, estabelecimento_id) VALUES
                                                                         (1,-23.55052, -46.633308, 11), -- São Paulo, Estabelecimento A
                                                                         (2,-22.9068, -43.1729, 22),    -- Rio de Janeiro, Estabelecimento B
                                                                         (3,-25.4284, -49.2733, 33);    -- Curitiba, Estabelecimento C

-- Dados de Avaliações
INSERT INTO tb_avaliacao (id,autor, nota, comentario, data_avaliacao, estabelecimento_id) VALUES
                                                                                           (11,'João Silva', 4.5, 'Ótimo atendimento!', NOW(), 11),
                                                                                           (22,'Maria Oliveira', 5.0, 'Adorei o corte!', NOW(), 22),
                                                                                           (33,'Pedro Almeida', 3.5, 'Poderia ser melhor.', NOW(), 33),
                                                                                           (44,'Carlos Almeida', 3.5, 'Poderia ser melhor.', NOW(), 33);

-- Dados de Agendamentos
INSERT INTO tb_agendamento (id,cliente_id, profissional_id, servico_id,estabelecimento_id, data_hora, status) VALUES
                                                                                            (11,1, 11, 11,11, '2024-10-15 10:00:00', 'ABERTO'),
                                                                                            (22,1, 11, 11,11, '2024-10-15 10:00:00', 'ABERTO'),
                                                                                            (33,2, 22, 22,22,'2024-10-15 11:30:00', 'CANCELADO'),
                                                                                            (44,3, 33, 33,33,'2024-10-16 15:00:00', 'ABERTO');

-- Dados de Fotos
INSERT INTO ESTABELECIMENTO_ENTITY_FOTOS (fotos, estabelecimento_entity_id) VALUES
                                                                                ('foto1.jpg', 11),
                                                                                ('foto2.jpg', 11),
                                                                                ('foto3.jpg', 22),
                                                                                ('foto4.jpg', 33);