package com.tienda.automoviles.ui;

import com.tienda.automoviles.dao.AutomovilDao;
import com.tienda.automoviles.model.Automovil;
import com.tienda.automoviles.service.AutomovilService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Ventana principal: integra catálogo (Read) + formulario (Create/Update) + botones Delete.
 * Orquesta las operaciones CRUD mediante AutomovilService.
 */
public class MainApp extends JFrame {

    private final AutomovilService service;
    private final CatalogoPanel catalogoPanel;
    private final AutomovilForm form;

    private final JButton guardarBtn = new JButton("Guardar");
    private final JButton limpiarBtn = new JButton("Nuevo");
    private final JButton eliminarBtn = new JButton("Eliminar seleccionado");
    private final JButton buscarBtn = new JButton(" Buscar por marca");
    private final JTextField buscarField = new JTextField(12);

    public MainApp() {
        super("Tienda de Automóviles — CRUD");
        this.service = new AutomovilService(new AutomovilDao());

        // Datos de ejemplo
        service.crear(new Automovil("Toyota", "Corolla", "1.8L 140cv", "Blanco", "ABC123", 4));
        service.crear(new Automovil("Ford", "Mustang", "5.0L V8", "Rojo", "DEF456", 2));
        service.crear(new Automovil("Volkswagen", "Golf", "2.0L TDI", "Gris", "GHI789", 5));
        service.crear(new Automovil("Peugeot", "208", "1.2L PureTech", "Negro", "JKL012", 3));

        catalogoPanel = new CatalogoPanel();
        form = new AutomovilForm();

        buildUI();
        refrescarTabla();
    }

    private void buildUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Catálogo", catalogoPanel);
        tabs.addTab("Registro", form);

        JPanel botones = new JPanel(new FlowLayout( FlowLayout.LEFT));
        botones.setBorder(new EmptyBorder(8, 12, 8, 12));

        buscarField.putClientProperty("JTextField.placeholder", "Marca...");
        buscarBtn.addActionListener(e -> performBuscar());
        buscarField.addActionListener(e -> performBuscar());

        botones.add(buscarField);
        botones.add(buscarBtn);
        botones.add(Box.createHorizontalStrut(12));
        botones.add(guardarBtn);
        botones.add(Box.createHorizontalStrut(8));
        botones.add(limpiarBtn);
        botones.add(Box.createHorizontalStrut(8));
        botones.add(eliminarBtn);

        add(tabs, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);

        guardarBtn.addActionListener(e -> performGuardar());
        limpiarBtn.addActionListener(e -> {
            form.limpiar();
            JOptionPane.showMessageDialog(this, "Formulario reiniciado para nuevo registro.");
        });
        eliminarBtn.addActionListener(e -> performEliminar());
    }

    private void performGuardar() {
        try {
            Automovil auto = form.getAutomovil();
            Long idEdicion = form.getIdEdicion();

            if (idEdicion == null) {
                Automovil creado = service.crear(auto);
                JOptionPane.showMessageDialog(this, "Creado con id " + creado.getId());
            } else {
                auto.setId(idEdicion);
                boolean ok = service.actualizar(auto);
                JOptionPane.showMessageDialog(this, ok ? "Actualizado." : "No se encontró el id " + idEdicion);
            }
            form.limpiar();
            refrescarTabla();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void performEliminar() {
        Long id = catalogoPanel.getAutoSeleccionado();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Seleccioná un auto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Eliminar el auto con id " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            boolean ok = service.eliminar(id);
            JOptionPane.showMessageDialog(this, ok ? "Eliminado." : "No existía.");
            refrescarTabla();
        }
    }

    private void performBuscar() {
        String q = buscarField.getText().trim();
        if (q.isEmpty()) {
            refrescarTabla();
            return;
        }
        catalogoPanel.cargarVehiculos(service.leerPorMarca(q));
    }

    private void refrescarTabla() {
        catalogoPanel.cargarVehiculos(service.leerTodos());
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}
        SwingUtilities.invokeLater(() -> new MainApp().setVisible(true));
    }
}