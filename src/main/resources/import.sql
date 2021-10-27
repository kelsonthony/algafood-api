insert into tab_cozinhas (id, nome_cozinha) values (1, 'Tailandesa');
insert into tab_cozinhas (id, nome_cozinha) values (2, 'Indiana');

insert into tab_restaurantes (nome_restaurante, taxa_frete, cozinha_id) values ('Arabe do Brasil', 35.70, 1);
insert into tab_restaurantes (nome_restaurante, taxa_frete, cozinha_id) values ('Cozinha Caipira', 55.84, 1);
insert into tab_restaurantes (nome_restaurante, taxa_frete, cozinha_id) values ('Restaurante Indiano', 55.84, 2);

insert into forma_pagamento (descricao_pagamento) values ('Descricao Pagamento 1');
insert into forma_pagamento (descricao_pagamento) values ('Descricao Pagamento 2');
insert into forma_pagamento (descricao_pagamento) values ('Descricao Pagamento 3');

insert into tab_permissao (nome_permissao, descricao_permissao) values ('Nome permissao 1', 'Descricao permissao 1');
insert into tab_permissao (nome_permissao, descricao_permissao) values ('Nome permissao 2', 'Descricao permissao 2');
insert into tab_permissao (nome_permissao, descricao_permissao) values ('Nome permissao 3', 'Descricao permissao 3');

insert into tab_estados (id, nome_estado) values (1, 'Sao Paulo');
insert into tab_estados (id, nome_estado) values (2, 'Goias');
insert into tab_estados (id, nome_estado) values (3, 'Distrito Federal');

insert into tab_cidades (nome_cidade, estado_id) values ('Campinas', 1);
insert into tab_cidades (nome_cidade, estado_id) values ('Goiania', 2);
insert into tab_cidades (nome_cidade, estado_id) values ('Aguas Claras', 3);