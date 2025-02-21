import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class PetShopApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Pets");
        frame.setSize(800, 750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        // Labels e campos de texto
        JLabel donoLabel = new JLabel("Nome do Dono:");
        donoLabel.setBounds(10, 20, 150, 25);
        panel.add(donoLabel);

        JTextField donoText = new JTextField(20);
        donoText.setBounds(150, 20, 200, 25);
        panel.add(donoText);

        JLabel animalLabel = new JLabel("Nome do Animal:");
        animalLabel.setBounds(10, 50, 150, 25);
        panel.add(animalLabel);

        JTextField animalText = new JTextField(20);
        animalText.setBounds(150, 50, 200, 25);
        panel.add(animalText);

        JLabel especieLabel = new JLabel("Espécie:");
        especieLabel.setBounds(10, 80, 150, 25);
        panel.add(especieLabel);

        JTextField especieText = new JTextField(20);
        especieText.setBounds(150, 80, 200, 25);
        panel.add(especieText);

        JLabel enderecoLabel = new JLabel("Endereço:");
        enderecoLabel.setBounds(10, 110, 150, 25);
        panel.add(enderecoLabel);

        JTextField enderecoText = new JTextField(20);
        enderecoText.setBounds(150, 110, 200, 25);
        panel.add(enderecoText);

        JLabel alergiaLabel = new JLabel("Alergia:");
        alergiaLabel.setBounds(10, 140, 150, 25);
        panel.add(alergiaLabel);

        JTextField alergiaText = new JTextField(20);
        alergiaText.setBounds(150, 140, 200, 25);
        panel.add(alergiaText);

        JLabel pesoLabel = new JLabel("Peso:");
        pesoLabel.setBounds(10, 170, 150, 25);
        panel.add(pesoLabel);

        JTextField pesoText = new JTextField(20);
        pesoText.setBounds(150, 170, 200, 25);
        panel.add(pesoText);

        JLabel idadeLabel = new JLabel("Idade:");
        idadeLabel.setBounds(10, 200, 150, 25);
        panel.add(idadeLabel);

        JTextField idadeText = new JTextField(20);
        idadeText.setBounds(150, 200, 200, 25);
        panel.add(idadeText);

        JLabel observacaoLabel = new JLabel("Observação:");
        observacaoLabel.setBounds(10, 230, 150, 25);
        panel.add(observacaoLabel);

        JTextField observacaoText = new JTextField(20);
        observacaoText.setBounds(150, 230, 200, 25);
        panel.add(observacaoText);

        // Botões
        // Botões
JButton salvarButton = new JButton("Salvar");
salvarButton.setBounds(10, 270, 100, 25); // Alinhado à esquerda
panel.add(salvarButton);

JButton carregarButton = new JButton("Carregar Pets");
carregarButton.setBounds(120, 270, 150, 25); // Ao lado do botão "Salvar"
panel.add(carregarButton);

JButton limparButton = new JButton("Limpar campos");
limparButton.setBounds(280, 270, 150, 25); // Ao lado do botão "Carregar Pets"
panel.add(limparButton);

JButton editarButton = new JButton("Editar");
editarButton.setBounds(10, 310, 100, 25); // Alinhado à esquerda, abaixo dos outros
panel.add(editarButton);

JButton deletarButton = new JButton("Excluir");
deletarButton.setBounds(120, 310, 150, 25); // Ao lado do botão "Atualizar"
panel.add(deletarButton);


        // Ações dos botões
        salvarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDono = donoText.getText();
                String nomeAnimal = animalText.getText();
                String especie = especieText.getText();
                String endereco = enderecoText.getText();
                String alergia = alergiaText.getText();
                double peso = Double.parseDouble(pesoText.getText());
                int idade = Integer.parseInt(idadeText.getText());
                String observacao = observacaoText.getText();

                PetShop pet = new PetShop(idade, nomeDono, nomeAnimal, especie, endereco, alergia, peso, idade, observacao);
                pet.salvarNoBanco();

                JOptionPane.showMessageDialog(null, "Pet cadastrado com sucesso!");
            }
        });

        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                donoText.setText("");
                animalText.setText("");
                especieText.setText("");
                enderecoText.setText("");
                alergiaText.setText("");
                pesoText.setText("");
                idadeText.setText("");
                observacaoText.setText("");
            }
        });
        
        // JTable para exibir os pets
String[] colunas = {"ID", "Nome do Dono", "Nome do Animal", "Espécie", "Peso", "Idade", "Alergia", "Observação"};
DefaultTableModel modeloTabela = new DefaultTableModel(null, colunas);
JTable tabelaPets = new JTable(modeloTabela);
JScrollPane scrollPane = new JScrollPane(tabelaPets);
scrollPane.setBounds(10, 350, 750, 300);
panel.add(scrollPane);

// Ação para carregar os dados na tabela
carregarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        modeloTabela.setRowCount(0); // Limpa a tabela antes de carregar novos dados
        ArrayList<PetShop> pets = PetShop.buscarTodosPets(); // Método para buscar todos os pets
        for (PetShop pet : pets) {
            Object[] row = {
                pet.getId(),
                pet.getNomeDono(),
                pet.getNomeAnimal(),
                pet.getEspecie(),
                pet.getPeso(),
                pet.getIdade(),
                pet.getAlergia(),
                pet.getObservacao()
            };
            modeloTabela.addRow(row); // Adiciona uma linha para cada pet encontrado
        }
    }
});


        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idStr = JOptionPane.showInputDialog("Informe o ID para editar:");
                if (idStr != null) {
                    int id = Integer.parseInt(idStr);
                    PetShop pet = PetShop.buscarPetPorId(id);
                    if (pet != null) {
                        // Abrir nova janela para edição
                        JFrame editFrame = new JFrame("Editar Pet");
                        editFrame.setSize(400, 400);
                        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        JPanel editPanel = new JPanel();
                        editPanel.setLayout(null);
                        editFrame.add(editPanel);

                        JLabel editDonoLabel = new JLabel("Nome do Dono:");
                        editDonoLabel.setBounds(10, 20, 150, 25);
                        editPanel.add(editDonoLabel);

                        JTextField editDonoText = new JTextField(pet.getNomeDono());
                        editDonoText.setBounds(150, 20, 200, 25);
                        editPanel.add(editDonoText);

                        JLabel editAnimalLabel = new JLabel("Nome do Animal:");
                        editAnimalLabel.setBounds(10, 50, 150, 25);
                        editPanel.add(editAnimalLabel);

                        JTextField editAnimalText = new JTextField(pet.getNomeAnimal());
                        editAnimalText.setBounds(150, 50, 200, 25);
                        editPanel.add(editAnimalText);

                        JLabel editEspecieLabel = new JLabel("Espécie:");
                        editEspecieLabel.setBounds(10, 80, 150, 25);
                        editPanel.add(editEspecieLabel);

                        JTextField editEspecieText = new JTextField(pet.getEspecie());
                        editEspecieText.setBounds(150, 80, 200, 25);
                        editPanel.add(editEspecieText);

                        JLabel editEnderecoLabel = new JLabel("Endereço:");
                        editEnderecoLabel.setBounds(10, 110, 150, 25);
                        editPanel.add(editEnderecoLabel);

                        JTextField editEnderecoText = new JTextField(pet.getEndereco());
                        editEnderecoText.setBounds(150, 110, 200, 25);
                        editPanel.add(editEnderecoText);

                        JLabel editAlergiaLabel = new JLabel("Alergia:");
                        editAlergiaLabel.setBounds(10, 140, 150, 25);
                        editPanel.add(editAlergiaLabel);

                        JTextField editAlergiaText = new JTextField(pet.getAlergia());
                        editAlergiaText.setBounds(150, 140, 200, 25);
                        editPanel.add(editAlergiaText);

                        JLabel editPesoLabel = new JLabel("Peso:");
                        editPesoLabel.setBounds(10, 170, 150, 25);
                        editPanel.add(editPesoLabel);

                        JTextField editPesoText = new JTextField(String.valueOf(pet.getPeso()));
                        editPesoText.setBounds(150, 170, 200, 25);
                        editPanel.add(editPesoText);

                        JLabel editIdadeLabel = new JLabel("Idade:");
                        editIdadeLabel.setBounds(10, 200, 150, 25);
                        editPanel.add(editIdadeLabel);

                        JTextField editIdadeText = new JTextField(String.valueOf(pet.getIdade()));
                        editIdadeText.setBounds(150, 200, 200, 25);
                        editPanel.add(editIdadeText);

                        JLabel editObservacaoLabel = new JLabel("Observação:");
                        editObservacaoLabel.setBounds(10, 230, 150, 25);
                        editPanel.add(editObservacaoLabel);

                        JTextField editObservacaoText = new JTextField(pet.getObservacao());
                        editObservacaoText.setBounds(150, 230, 200, 25);
                        editPanel.add(editObservacaoText);

                        JButton salvarEditButton = new JButton("Salvar Alterações");
                        salvarEditButton.setBounds(100, 280, 150, 25);
                        editPanel.add(salvarEditButton);

                        salvarEditButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                pet.setNomeDono(editDonoText.getText());
                                pet.setNomeAnimal(editAnimalText.getText());
                                pet.setEspecie(editEspecieText.getText());
                                pet.setEndereco(editEnderecoText.getText());
                                pet.setAlergia(editAlergiaText.getText());
                                pet.setPeso(Double.parseDouble(editPesoText.getText()));
                                pet.setIdade(Integer.parseInt(editIdadeText.getText()));
                                pet.setObservacao(editObservacaoText.getText());
                                pet.atualizarNoBanco(id);

                                JOptionPane.showMessageDialog(null, "Pet atualizado com sucesso!");
                                editFrame.dispose();
                            }
                        });

        deletarButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Solicita o ID ao usuário
        String idStr = JOptionPane.showInputDialog("Informe o ID do pet para excluir:");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);

                // Chama o método para deletar o pet no banco de dados
                PetShop.deletarNoBanco(id);

                // Atualiza a tabela removendo o pet excluído
                modeloTabela.setRowCount(0); // Limpa a tabela
                ArrayList<PetShop> pets = PetShop.buscarTodosPets();
                for (PetShop pet : pets) {
                    Object[] row = {
                        pet.getId(),
                        pet.getNomeDono(),
                        pet.getNomeAnimal(),
                        pet.getEspecie(),
                        pet.getPeso(),
                        pet.getIdade(),
                        pet.getAlergia(),
                        pet.getObservacao()
                    };
                    modeloTabela.addRow(row); // Recarrega a tabela
                }

                JOptionPane.showMessageDialog(null, "Pet excluído com sucesso!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ID inválido. Por favor, informe um número válido.");
            }
        }
    }
});

                        editFrame.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Pet não encontrado!");
                    }
                }
            }
        });

        frame.setVisible(true);
    }
}
