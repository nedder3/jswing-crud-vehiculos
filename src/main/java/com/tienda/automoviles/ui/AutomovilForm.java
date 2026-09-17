package com.tienda.automoviles.ui;

import com.tienda.automoviles.model.Automovil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Formulario para crear o editar un automóvil.
 * Campos: marca, modelo, motor, color, puertas, patente.
 */
public class AutomovilForm extends JPanel {

    private final JTextField marcaField = new JTextField(15);
    private final JTextField modeloField = new JTextField(15);
    private final JTextField motorField = new JTextField(15);
    private final JTextField colorField = new JTextField(15);
    private final JTextField patenteField = new JTextField(15);
    private final JSpinner puertasSpinner = new JSpinner(new SpinnerNumberModel(4, 2, 6, 1));

    private Automovil automovilExistente; // null = modo Crear

    public AutomovilForm() {
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(12, 12, 12, 12));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        addLabel(gbc, "Marca:", 0, y);
        gbc.gridx = 1; add(marcaField, gbc); y++;

        addLabel(gbc, "Modelo:", 0, y);
        gbc.gridx = 1; add(modeloField, gbc); y++;

        addLabel(gbc, "Motor:", 0, y);
        gbc.gridx = 1; add(motorField, gbc); y++;

        addLabel(gbc, "Color:", 0, y);
        gbc.gridx = 1; add(colorField, gbc); y++;

        addLabel(gbc, "Puertas:", 0, y);
        gbc.gridx = 1; add(puertasSpinner, gbc); y++;

        addLabel(gbc, "Patente:", 0, y);
        gbc.gridx = 1; add(patenteField, gbc); y++;
    }

    private void addLabel(GridBagConstraints gbc, String text, int x, int y) {
        GridBagConstraints labelGbc = new GridBagConstraints();
        labelGbc.insets = new Insets(6, 6, 6, 6);
        labelGbc.fill = GridBagConstraints.HORIZONTAL;
        labelGbc.gridx = x;
        labelGbc.gridy = y;
        add(new JLabel(text), labelGbc);
    }

    /** Rellena el formulario con un automóvil existente (modo edición). */
    public void setAutomovil(Automovil automovil) {
        this.automovilExistente = automovil;
        if (automovil != null) {
            marcaField.setText(automovil.getMarca());
            modeloField.setText(automovil.getModelo());
            motorField.setText(automovil.getMotor());
            colorField.setText(automovil.getColor());
            patenteField.setText(automovil.getPatente());
            puertasSpinner.setValue(automovil.getCantidadPuertas());
        } else {
            limpiar();
        }
    }

    /** Devuelve los datos del formulario como un objeto Automovil (sin id). */
    public Automovil getAutomovil() {
        return new Automovil(
                marcaField.getText().trim(),
                modeloField.getText().trim(),
                motorField.getText().trim(),
                colorField.getText().trim(),
                patenteField.getText().trim(),
                (int) puertasSpinner.getValue()
        );
    }

    /** Id del auto en edición, o null si es creación. */
    public Long getIdEdicion() {
        return automovilExistente != null ? automovilExistente.getId() : null;
    }

    public void limpiar() {
        automovilExistente = null;
        marcaField.setText("");
        modeloField.setText("");
        motorField.setText("");
        colorField.setText("");
        patenteField.setText("");
        puertasSpinner.setValue(4);
    }
}