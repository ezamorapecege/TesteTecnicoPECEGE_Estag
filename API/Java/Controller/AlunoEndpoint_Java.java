package myapi.controllers;

import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import myapi.models.Aluno;
import org.springframework.http.ResponseEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    private static final Logger logger = LoggerFactory.getLogger(AlunoController.class);

    @PostMapping("/GetAllAlunos")
    public ResponseEntity<List<Aluno>> getAllAlunos() {
        logger.info("Recebendo requisição para listar alunos.");

        List<Aluno> alunos = new ArrayList<>();

        // Simulação de conexão SQL
        String connectionString = "FinjaQueAConexaoEstaCorreta";
        Connection connection = DriverManager.getConnection(connectionString);
        logger.info("Conexão com o banco de dados aberta com sucesso.");

        String query = "SELECT * FROM Aluno";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        alunos = mapAlunosFromResultSet(resultSet);  // Considere que já existe um método que mapeará as entidades automaticamente para uma lista de objetos do tipo Aluno

        logger.info("Lista de alunos retornada com sucesso.");
        return ResponseEntity.ok(alunos);
    }
}