/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  siux
 * Created: Nov 14, 2020
 */

drop table chemical_element if exists;

create table chemical_element(
    id INT IDENTITY NOT NULL,
    name VARCHAR(200),
    is_metal BOOLEAN,
    state varchar(200)
);

insert into chemical_element (name, is_metal, state) values('Hydrogen', false, 'Gas');
insert into chemical_element (name, is_metal, state) values('Helium', false, 'Gas');
insert into chemical_element (name, is_metal, state) values('Gold', true, 'Solid');