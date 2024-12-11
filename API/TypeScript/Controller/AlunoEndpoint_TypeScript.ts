import { Request, Response } from 'express';
import { createConnection } from 'mysql2/promise';
import Aluno from '../Model/Aluno';

class AlunoController {
    private connectionString: string;

    constructor() {
        this.connectionString = 'FinjaQueAConexaoEstaCorreta';
    }

    public async getAllAlunos(req: Request, res: Response): Promise<void> {
        console.log('Recebendo requisição para listar todos os alunos.');

        let alunos: Aluno[] = [];

        // Simulação de conexão SQL
        const connection = await createConnection({
            uri: this.connectionString,
        });

        console.log('Conexão com o banco de dados aberta com sucesso.');

        const query = 'SELECT * FROM Alunos';
        const [rows] = await connection.execute(query);

        alunos = this.mapAlunosFromResult(rows);  // Considere que já existe um método que mapeará as entidades automaticamente para uma lista de objetos do tipo Aluno

        console.log('Lista de alunos retornada com sucesso.');
        res.json(alunos);
    }
}

export default AlunoController;
