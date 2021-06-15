package br.com.henricker.views;

import br.com.henricker.controllers.CategoryController;
import br.com.henricker.controllers.ProductController;
import br.com.henricker.models.Category;
import br.com.henricker.models.Product;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class ProductCategoryFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel labelNome, labelDescricao, labelCategoria;
	private JTextField textoNome, textoDescricao;
	private JComboBox<Category> comboCategoria;
	private JButton botaoSalvar, botaoEditar, botaoLimpar, botarApagar;
	private JTable tabela;
	private DefaultTableModel modelo;
	private ProductController productController;
	private CategoryController categoryController;

	public ProductCategoryFrame() {
		super("Products");
		Container container = getContentPane();
		setLayout(null);

		this.categoryController = new CategoryController();
		this.productController = new ProductController();

		labelNome = new JLabel("Name of Product");
		labelDescricao = new JLabel("Description of product");
		labelCategoria = new JLabel("Category of Product");

		labelNome.setBounds(10, 10, 240, 15);
		labelDescricao.setBounds(10, 50, 240, 15);
		labelCategoria.setBounds(10, 90, 240, 15);

		labelNome.setForeground(Color.BLACK);
		labelDescricao.setForeground(Color.BLACK);
		labelCategoria.setForeground(Color.BLACK);

		container.add(labelNome);
		container.add(labelDescricao);
		container.add(labelCategoria);

		textoNome = new JTextField();
		textoDescricao = new JTextField();
		comboCategoria = new JComboBox<Category>();

		comboCategoria.addItem(new Category(0, "Select"));
		List<Category> categorias = this.listarCategoria();
		for (Category categoria : categorias) {
			comboCategoria.addItem(categoria);
		}

		textoNome.setBounds(10, 25, 265, 20);
		textoDescricao.setBounds(10, 65, 265, 20);
		comboCategoria.setBounds(10, 105, 265, 20);

		container.add(textoNome);
		container.add(textoDescricao);
		container.add(comboCategoria);

		botaoSalvar = new JButton("Save");
		botaoLimpar = new JButton("Clean");

		botaoSalvar.setBounds(10, 145, 80, 20);
		botaoLimpar.setBounds(100, 145, 80, 20);

		container.add(botaoSalvar);
		container.add(botaoLimpar);

		tabela = new JTable();
		modelo = (DefaultTableModel) tabela.getModel();

		modelo.addColumn("Identified of Product");
		modelo.addColumn("Name of Product");
		modelo.addColumn("Description of product");

		preencherTabela();

		tabela.setBounds(10, 185, 760, 300);
		container.add(tabela);

		botarApagar = new JButton("Delete");
		botaoEditar = new JButton("Update");

		botarApagar.setBounds(10, 500, 80, 20);
		botaoEditar.setBounds(100, 500, 80, 20);

		container.add(botarApagar);
		container.add(botaoEditar);

		setSize(800, 600);
		setVisible(true);
		setLocationRelativeTo(null);

		botaoSalvar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				salvar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoLimpar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});

		botarApagar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletar();
				limparTabela();
				preencherTabela();
			}
		});

		botaoEditar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				alterar();
				limparTabela();
				preencherTabela();
			}
		});
	}

	private void limparTabela() {
		modelo.getDataVector().clear();
	}

	private void alterar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			String nome = (String) modelo.getValueAt(tabela.getSelectedRow(), 1);
			String descricao = (String) modelo.getValueAt(tabela.getSelectedRow(), 2);
			this.productController.update(nome, descricao, id);
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecionar o ID");
		}
	}

	private void deletar() {
		Object objetoDaLinha = (Object) modelo.getValueAt(tabela.getSelectedRow(), tabela.getSelectedColumn());
		if (objetoDaLinha instanceof Integer) {
			Integer id = (Integer) objetoDaLinha;
			this.productController.delete(id);
			modelo.removeRow(tabela.getSelectedRow());
			JOptionPane.showMessageDialog(this, "Item deleted with successfully!");
		} else {
			JOptionPane.showMessageDialog(this, "Please, select the id!");
		}
	}

	private void preencherTabela() {
		List<Product> produtos = listarProduto();
		try {
			for (Product product : produtos) {
				modelo.addRow(new Object[] { product.getId(), product.getName(), product.getDescription() });
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private List<Category> listarCategoria() {
		return this.categoryController.list();
	}

	private void salvar() {
		if (!textoNome.getText().equals("") && !textoDescricao.getText().equals("")) {
			Product product = new Product(textoNome.getText(), textoDescricao.getText());
			Category category = (Category) comboCategoria.getSelectedItem();
			product.setCategoryId(category.getId());
			this.productController.save(product);
			JOptionPane.showMessageDialog(this, "Save with successfully!");
			this.limpar();
		} else {
			JOptionPane.showMessageDialog(this, "Name and description not been null");
		}
	}

	private List<Product> listarProduto() {
		return this.productController.list();
	}

	private void limpar() {
		this.textoNome.setText("");
		this.textoDescricao.setText("");
		this.comboCategoria.setSelectedIndex(0);
	}
}
