import desafio.dominio.*;

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

public class Window {

    static JFrame frame;
    static JMenuItem itemConteudoCadastrar;
    static JMenuItem itemConteudoConsultar;
    static JMenuItem itemDevCadastrar;
    static JMenuItem itemDevConsultar;
    static JMenuItem itemBootcampCadastrar;
    static JMenuItem itemBootcampConsultar;
    static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
    static List<Conteudo> conteudos = new ArrayList<>();
    static List<Dev> devs = new ArrayList<>();
    JMenuBar menuBar;
    static List<Bootcamp> bootcamps = new ArrayList<>();


    public Window() {
        frame = new JFrame("Bootcamp");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.setLayout(new GridLayout(10, 2));

        menuBar = new JMenuBar();
        JMenu menuConteudo = new JMenu("Conteúdo");
        menuBar.add(menuConteudo);

        JMenu menuDev = new JMenu("Dev");
        menuBar.add(menuDev);

        JMenu menuBootcamp = new JMenu("Bootcamp");
        menuBar.add(menuBootcamp);


        itemConteudoCadastrar = new JMenuItem("Cadastrar");
        menuConteudo.add(itemConteudoCadastrar);
        itemConteudoConsultar = new JMenuItem("Consultar");
        menuConteudo.add(itemConteudoConsultar);

        itemDevCadastrar = new JMenuItem("Cadastrar");
        menuDev.add(itemDevCadastrar);
        itemDevConsultar = new JMenuItem("Consultar");
        menuDev.add(itemDevConsultar);

        itemBootcampCadastrar = new JMenuItem("Cadastrar");
        menuBootcamp.add(itemBootcampCadastrar);
        itemBootcampConsultar = new JMenuItem("Consultar");
        menuBootcamp.add(itemBootcampConsultar);

        ActionListener alCadastrarConteudo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                desabilitaMenu();
                cadastraConteudo();
            }
        };
        itemConteudoCadastrar.addActionListener(alCadastrarConteudo);

        ActionListener alConteudoConsultar = e -> consultarConteudo();
        itemConteudoConsultar.addActionListener(alConteudoConsultar);

        ActionListener alCadastrarDev = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                desabilitaMenu();
                cadastraDev();
            }
        };
        itemDevCadastrar.addActionListener(alCadastrarDev);

        ActionListener alDevConsultar = (e) -> {
            frame.setVisible(false);
            desabilitaMenu();
            consultarDevs();
        };
        itemDevConsultar.addActionListener(alDevConsultar);

        ActionListener alCadastrarBootcamp = (e) -> {
          frame.setVisible(false);
          desabilitaMenu();
          cadastrarBootcamp();
        };
        itemBootcampCadastrar.addActionListener(alCadastrarBootcamp);

        ActionListener alConsultarBootcamp = (e) -> {
            frame.setVisible(false);
            desabilitaMenu();
            consultarBootcamp();
        };
        itemBootcampConsultar.addActionListener(alConsultarBootcamp);

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try {
            conteudos = (List<Conteudo>) IO.lerConteudo("conteudos");
            System.out.println(conteudos);
        } catch (FileNotFoundException fne) {
            JOptionPane.showMessageDialog(null, "Não há nenhum conteúdo salvo");
        }

        try {
            devs = (List<Dev>) IO.lerConteudo("devs");
            System.out.println(devs);
        } catch (FileNotFoundException fne) {
            JOptionPane.showMessageDialog(null, "Não há nenhum dev salvo");
        }

        try {
            bootcamps = (List<Bootcamp>) IO.lerConteudo("bootcamps");
            System.out.println(bootcamps);
        } catch (FileNotFoundException fne) {
            JOptionPane.showMessageDialog(null, "Não há nenhum bootcamp salvo");
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Window();
            }
        });
    }

    private static void cadastraConteudo() {

        frame.setLayout(new GridLayout(10, 2));

        JLabel lbl = new JLabel("CADASTRAR CONTEÚDO:");
        frame.add(lbl);

        JLabel lblSpace = new JLabel("");
        frame.add(lblSpace);

        JLabel lbl1 = new JLabel("Tipo:");
        frame.add(lbl1);
        JLabel lblSpace1 = new JLabel("");
        frame.add(lblSpace1);

        JRadioButtonMenuItem radioCurso = new JRadioButtonMenuItem("Curso");
        JRadioButtonMenuItem radioMentoria = new JRadioButtonMenuItem("Mentoria");
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioCurso);
        bg.add(radioMentoria);
        frame.add(radioCurso);
        frame.add(radioMentoria);

        JLabel lbl2 = new JLabel("Titulo:");
        frame.add(lbl2);
        JTextField tfTitulo = new JTextField(15);
        frame.add(tfTitulo);

        JLabel lbl3 = new JLabel("Descrição:");
        frame.add(lbl3);
        JTextField tfDescricao = new JTextField(15);
        frame.add(tfDescricao);

        JLabel lbl4 = new JLabel("Carga horária:");
        lbl4.setVisible(false);
        JTextField tfCargaHoraria = new JTextField(5);
        tfCargaHoraria.setVisible(false);
        frame.add(lbl4);
        frame.add(tfCargaHoraria);

        JLabel lbl5 = new JLabel("Data:");
        lbl5.setVisible(false);
        JTextField tfDataMentoria = new JTextField();
        tfDataMentoria.setVisible(false);
        frame.add(lbl5);
        frame.add(tfDataMentoria);



        JButton btnCadastrar = new JButton("Cadastrar");
        frame.add(btnCadastrar);
        btnCadastrar.setEnabled(false);

        JButton btnCancelar = new JButton("Cancelar");
        frame.add(btnCancelar);

        FocusListener fl = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    Integer.parseInt(tfCargaHoraria.getText());
                    if (!tfCargaHoraria.getText().isEmpty() && radioCurso.isSelected()) {
                        btnCadastrar.setEnabled(true);
                    } else {
                        btnCadastrar.setEnabled(false);
                    }
                } catch (IllegalArgumentException iae) {
                    JOptionPane.showMessageDialog(null, "Digite um número inteiro");
                }
            }
        };
        tfCargaHoraria.addFocusListener(fl);

        FocusListener flData = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try {
                    LocalDate dataConvertida = LocalDate.parse(tfDataMentoria.getText(), dateFormat);
                    btnCadastrar.setEnabled(true);
                } catch (Exception dte) {
                    JOptionPane.showMessageDialog(null,"Digite uma data válida, no formato 'dd/mm/aaaa'");
                }
            }
        };
        tfDataMentoria.addFocusListener(flData);

        ActionListener alRadio = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (radioCurso.isSelected()) {
                    lbl4.setVisible(true);
                    tfCargaHoraria.setVisible(true);
                } else {
                    lbl4.setVisible(false);
                    tfCargaHoraria.setVisible(false);
                }
                if (radioMentoria.isSelected()) {
                    lbl5.setVisible(true);
                    tfDataMentoria.setVisible(true);
                } else {
                    lbl5.setVisible(false);
                    tfDataMentoria.setVisible(false);
                }
            }
        };
        radioCurso.addActionListener(alRadio);
        radioMentoria.addActionListener(alRadio);


        ActionListener al = e -> {

            if (radioCurso.isSelected() || radioMentoria.isSelected()) {
                if (!tfTitulo.getText().isEmpty() && !tfDescricao.getText().isEmpty()) {
                    if ((radioCurso.isSelected() && !tfCargaHoraria.getText().isEmpty()) || (radioMentoria.isSelected() &&!tfDataMentoria.getText().isEmpty())) {
                        if (radioCurso.isSelected()) {
                            try {
                                int cargaHoraria = Integer.parseInt(tfCargaHoraria.getText());
                                Curso c = new Curso();
                                c.setTitulo(tfTitulo.getText());
                                c.setDescricao(tfDescricao.getText());
                                c.setCargaHoraria(cargaHoraria);
                                try {
                                    conteudos.add(c);
                                    IO.salvarConteudo(conteudos, "conteudos");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }

                            } catch (IllegalArgumentException iae) {
                                JOptionPane.showMessageDialog(null, "Digite um número inteiro");
                            }

                        } else if (radioMentoria.isSelected()) {
                            Mentoria m = new Mentoria();
                            m.setTitulo(tfTitulo.getText());
                            m.setDescricao(tfDescricao.getText());
                            m.setData(LocalDate.parse(tfDataMentoria.getText(), dateFormat));
                            try {
                                conteudos.add(m);
                                IO.salvarConteudo(conteudos, "conteudos");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }

                        }
                        JOptionPane.showMessageDialog(null, "Conteúdo cadastrado com sucesso.");
                        removeCadastrarConteudo(lbl, lbl1, lbl2, lbl3, lbl4, lbl5, btnCadastrar, radioCurso, radioMentoria, tfTitulo, tfDescricao, tfCargaHoraria, tfDataMentoria, btnCancelar, lblSpace, lblSpace1);
                        Window.habilitaMenu();
                    } else {
                        JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione a opção 'Curso' ou a 'Mentoria'");
            }
        };
        btnCadastrar.addActionListener(al);

        ActionListener alCancel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window.habilitaMenu();
                removeCadastrarConteudo(lbl, lbl1, lbl2, lbl3, lbl4, lbl5, btnCadastrar, radioCurso, radioMentoria, tfTitulo, tfDescricao, tfCargaHoraria, tfDataMentoria, btnCancelar, lblSpace, lblSpace1);
            }
        };
        btnCancelar.addActionListener(alCancel);

        frame.setVisible(true);
    }

    private static void removeCadastrarConteudo(JLabel lbl, JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4,
                                                JLabel lbl5, JButton btnCadastrar, JRadioButtonMenuItem radioCurso,
                                                JRadioButtonMenuItem radioMentoria, JTextField tfTitulo,
                                                JTextField tfDescricao, JTextField tfCargaHoraria, JTextField tfDataMentoria,
                                                JButton btnCancelar, JLabel lblSpace, JLabel lblSpace1) {
        frame.remove(lbl);
        frame.remove(lbl1);
        frame.remove(lbl2);
        frame.remove(lbl3);
        frame.remove(lbl4);
        frame.remove(lbl5);
        frame.remove(btnCadastrar);
        frame.remove(radioCurso);
        frame.remove(radioMentoria);
        frame.remove(tfTitulo);
        frame.remove(tfDescricao);
        frame.remove(tfCargaHoraria);
        frame.remove(tfDataMentoria);
        frame.remove(btnCancelar);
        frame.remove(lblSpace);
        frame.remove(lblSpace1);
        frame.setVisible(false);
        frame.setVisible(true);
    }

    private void cadastraDev() {

        frame.setLayout(new GridLayout(10, 2));

        JLabel lbl = new JLabel("CADASTRAR DEV:");
        frame.add(lbl);

        JLabel lblSpace = new JLabel("");
        frame.add(lblSpace);

        JLabel lblNome = new JLabel("Nome:");
        frame.add(lblNome);

        JTextField tfNome = new JTextField();
        frame.add(tfNome);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setEnabled(false);
        frame.add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        frame.add(btnCancelar);

        FocusListener fl = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (!tfNome.getText().isEmpty()) {
                    btnCadastrar.setEnabled(true);
                }
            }
        };
        tfNome.addFocusListener(fl);


        ActionListener alCadastrar = e -> {
            Dev dev = new Dev();
            dev.setNome(tfNome.getText());
            devs.add(dev);
            try {
                IO.salvarConteudo(devs, "devs");
                JOptionPane.showMessageDialog(null, "Dev cadastrado com sucesso.");
                itemDevCadastrar.setEnabled(true);
                removeCadastrarDev(lbl, lblNome, tfNome, btnCadastrar, btnCancelar, lblSpace);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
        btnCadastrar.addActionListener(alCadastrar);

        ActionListener alCancelar = e -> {
            removeCadastrarDev(lbl, lblNome, tfNome, btnCadastrar, btnCancelar, lblSpace);
            itemDevCadastrar.setEnabled(true);
        };
        btnCancelar.addActionListener(alCancelar);

        frame.setVisible(true);

    }

    private void removeCadastrarDev(JLabel lbl, JLabel lblNome, JTextField tfNome, JButton btnCadastrar, JButton btnCancelar, JLabel lblSpace) {
        frame.remove(lbl);
        frame.remove(lblNome);
        frame.remove(tfNome);
        frame.remove(btnCadastrar);
        frame.remove(btnCancelar);
        frame.remove(lblSpace);
        frame.setVisible(false);
        frame.setVisible(true);
    }

    private void consultarConteudo() {

        frame.setLayout(new GridLayout(2,1));
        desabilitaMenu();

        JTextArea jTextArea = new JTextArea("CONTEÚDOS CADASTRADOS:\n\nTipo  \tTítulo    \tDescrição\t\tCargaHorária/Data\n");
        jTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(jTextArea);

        for (Conteudo c: conteudos) {
            String dado = "";
            if (c.getClass().getSimpleName().equals("Curso")) {
                Curso curso = (Curso) c;
                dado = String.valueOf(curso.getCargaHoraria());
            } else {
                Mentoria mentoria = (Mentoria) c;
                dado = String.valueOf(mentoria.getData());
            }
            jTextArea.append(c.getClass().getSimpleName() + "\t" + c.getTitulo()+ "\t" + c.getDescricao() + "\t\t"
                    + dado + "\n");
        }

        JButton btnSair = new JButton("Sair");

        frame.add(scrollPane);
        frame.add(btnSair);

        ActionListener alSair = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(scrollPane);
                frame.remove(btnSair);
                habilitaMenu();
                frame.setVisible(false);
                frame.setVisible(true);
            }
        };
        btnSair.addActionListener(alSair);

        frame.setVisible(true);

    }

    private void consultarDevs() {

        frame.setLayout(new GridLayout(2,1));
        desabilitaMenu();

        JTextArea jTextArea = new JTextArea("DEVs CADASTRADOS:\n\n");
        jTextArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(jTextArea);

        for (Dev d: devs) {
            jTextArea.append(d.getNome() + "\n");
        }

        JButton btnSair = new JButton("Sair");

        frame.add(scrollPane);
        frame.add(btnSair);

        ActionListener alSair = e -> {
            frame.remove(scrollPane);
            frame.remove(btnSair);
            habilitaMenu();
            frame.setVisible(false);
            frame.setVisible(true);
        };
        btnSair.addActionListener(alSair);

        frame.setVisible(true);

    }

    private void cadastrarBootcamp () {

        frame.setLayout(new GridLayout(9, 1));

        JLabel lbl = new JLabel("CADASTRAR BOOTCAMP:");
        frame.add(lbl);
        JLabel jLabelNome = new JLabel("Nome:");
        JTextField jTextFieldNome = new JTextField();
        frame.add (jLabelNome);
        frame.add(jTextFieldNome);

        JLabel jLabelDescricao = new JLabel("Descricao:");
        JTextField jTextFieldDescricao = new JTextField();
        frame.add (jLabelDescricao);
        frame.add(jTextFieldDescricao);

        Conteudo conteudoString[] = new Conteudo[conteudos.size()];
        for (int i = 0; i < conteudos.size(); i++) {
            conteudoString[i] = conteudos.get(i);
        }

        JLabel jLabelConteudo = new JLabel("Conteudo:  (mantenha CTRL pressionado para mais de uma opção)");
        frame.add(jLabelConteudo);
        JList<Conteudo> selectConteudo = new JList<>(conteudoString);
        selectConteudo.setSelectionMode(JList.HORIZONTAL_WRAP);
        selectConteudo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        selectConteudo.setLayoutOrientation(JList.HORIZONTAL_WRAP);

        JScrollPane scrollLista = new JScrollPane(selectConteudo);

        frame.add(scrollLista);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setEnabled(false);
        frame.add(btnCadastrar);

        JButton btnCancelar = new JButton("Cancelar");
        frame.add(btnCancelar);

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!selectConteudo.getSelectedValuesList().isEmpty() && !jTextFieldNome.getText().isEmpty() && !jTextFieldDescricao.getText().isEmpty()) {
                    btnCadastrar.setEnabled(true);
                }
            }
        };
        selectConteudo.addListSelectionListener(listSelectionListener);

        ActionListener alCadastrar = (e) -> {
            Bootcamp b = new Bootcamp();
            b.setNome(jTextFieldNome.getText());
            b.setDescricao(jTextFieldDescricao.getText());
            b.setConteudos(selectConteudo.getSelectedValuesList().stream().collect(Collectors.toSet()));
            bootcamps.add(b);
            try {
                IO.salvarConteudo(bootcamps, "bootcamps");
                JOptionPane.showMessageDialog(null, "Bootcamp salvo.");
                removeCadastrarBootcamp(lbl, jLabelNome, jTextFieldNome, jLabelDescricao, jTextFieldDescricao, jLabelConteudo, scrollLista, btnCadastrar, btnCancelar);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        };
        btnCadastrar.addActionListener(alCadastrar);

        ActionListener alCancelar = (e)-> {
            removeCadastrarBootcamp(lbl, jLabelNome, jTextFieldNome, jLabelDescricao, jTextFieldDescricao, jLabelConteudo, scrollLista, btnCadastrar, btnCancelar);
        };
        btnCancelar.addActionListener(alCancelar);

        frame.setVisible(true);
    }

    private void removeCadastrarBootcamp(JLabel lbl, JLabel jLabelNome, JTextField jTextFieldNome, JLabel jLabelDescricao, JTextField jTextFieldDescricao, JLabel jLabelConteudo, JScrollPane scrollLista, JButton btnCadastrar, JButton btnCancelar) {
        frame.remove(lbl);
        frame.remove(jLabelNome);
        frame.remove(jTextFieldNome);
        frame.remove(jLabelDescricao);
        frame.remove(jTextFieldDescricao);
        frame.remove(jLabelConteudo);
        frame.remove(scrollLista);
        frame.remove(btnCadastrar);
        frame.remove(btnCancelar);
        habilitaMenu();
        frame.setVisible(false);
        frame.setVisible(true);
    }

    private void consultarBootcamp() {

    }

    private void desabilitaMenu() {
        itemConteudoCadastrar.setEnabled(false);
        itemConteudoConsultar.setEnabled(false);
        itemDevCadastrar.setEnabled(false);
        itemDevConsultar.setEnabled(false);
        itemBootcampCadastrar.setEnabled(false);
        itemBootcampConsultar.setEnabled(false);
    }

    private static void habilitaMenu() {
        itemConteudoCadastrar.setEnabled(true);
        itemConteudoConsultar.setEnabled(true);
        itemDevCadastrar.setEnabled(true);
        itemDevConsultar.setEnabled(true);
        itemBootcampCadastrar.setEnabled(true);
        itemBootcampConsultar.setEnabled(true);
    }
}
