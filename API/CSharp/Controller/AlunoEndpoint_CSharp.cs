using Microsoft.AspNetCore.Mvc;
using MyApi.Model.Aluno;

namespace MyApi.Controllers
{
    [ApiController]
    [Route("api/[controller]")]
    public class AlunoController : ControllerBase
    {
        private readonly ILogger<AlunoController> _logger;

        public AlunoController(ILogger<AlunoController> logger)
        {
            _logger = logger;
        }


        [HttpPost("GetAllAlunos")]
        public IActionResult GetAllAlunos()
        {
            _logger.LogInformation("Recebendo requisição para listar alunos.");

            var alunos = new List<Aluno>();

            // Simulação de conexão SQL
            var connectionString = "FinjaQueAConexaoEstaCorreta";
            using (var connection = new SqlConnection(connectionString))
            {
                connection.Open();
                _logger.LogInformation("Conexão com o banco de dados aberta com sucesso.");

                var query = "SELECT * FROM Aluno";
                using (var command = new SqlCommand(query, connection))
                {
                    using (var reader = command.ExecuteReader()) 
                    {
                        alunos = MapeiaAlunosFromReader(reader); // Considere que já existe um método que mapeará as entidades automaticamente para uma lista de objetos do tipo Aluno
                    }
                }
            }

            _logger.LogInformation("Lista de alunos retornada com sucesso.");
            return Ok(alunos);
        }
    }   
}