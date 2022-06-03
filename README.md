
Orientação a Objetos - Java

Trata-se de um projeto simples para a prática de orientação a objetos, desenvolvido em curso da Dio.

Em relação ao projeto original, fiz algumas modificações nas classes e incluí uma view para cadastrar os Devs, cursos e Bootcamps.
Além disso incluí uma forma de persistir os dados.

Utilizei as seguintes bibliotecas do Java:

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

Para desenvolver a view em Swing/Awt não utilizei nenhuma ferramenta de apoio, tornando necessário aprofundar o estudo do Swing/Awt.
Fiz essa opção por já dominar OO, objeto do treinamento, como um desafio pessoal para não depender dessas derramentas, muitas pagas.

Alguns pequenos acréscimos podem ser incluídos como desafio a quem queira praticar:
Permitir a inscrição de um Dev em um curso ou Bootcamp;
Permitir a exclusão dos itens cadastrados;


Pré-requisitos para a execução do projeto:

Intellij ou outra IDE de preferência.
