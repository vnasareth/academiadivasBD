package edu.poo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FichaTreinoDAOImpl implements IAcademiaDAO<FichaTreino> {

	private final static String JDBC_CLASS = "org.mariadb.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mariadb://localhost:3306/academiadb?allowMultiQueries=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASS = "";
    private Connection con;

    public FichaTreinoDAOImpl() {
        try {
            Class.forName(JDBC_CLASS);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public void inserir(FichaTreino treino) {
		String sql = "INSERT INTO treino (id, id_aluno, id_professor, data, tipo, info) ";
		sql += " VALUES (0, ?, ?, ?, ?, ?) ";
		try {
            PreparedStatement stmt = con.prepareStatement(sql);
            Aluno aluno = treino.getAluno();
            stmt.setInt(1, aluno.getId());
            Professor professor = treino.getProfessor();
            stmt.setInt(2, professor.getId());
            stmt.setDate(3, java.sql.Date.valueOf(treino.getDataTreino()));
            stmt.setString(4, treino.getTipoTreino());
            stmt.setString(5, treino.getInfoAdicional());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public List<FichaTreino> consultar(String nome) {
		IAcademiaDAO<Aluno> daoA = new AlunosDAOImpl();
		IAcademiaDAO<Professor> daoP = new ProfessoresDAOImpl();
		List<FichaTreino> lista = new ArrayList<>();
        String sql = "SELECT t.id, a.nome as 'nome_aluno', p.nome as 'nome_professor', t.data, t.tipo, t.info "
        		+ " FROM professor p, aluno a, treino t "
        		+ " WHERE t.id_aluno = a.id AND t.id_professor = p.id AND a.nome LIKE '%" + nome + "%'";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	FichaTreino treino = new FichaTreino();
                treino.setId(rs.getInt("id"));
                treino.setDataTreino(rs.getDate("data").toLocalDate());
                treino.setTipoTreino(rs.getString("tipo"));
                treino.setInfoAdicional(rs.getString("info"));
                Aluno aluno = ((AlunosDAOImpl) daoA).consultarAluno(rs.getString("nome_aluno"));
                treino.setAluno(aluno);
                Professor professor = ((ProfessoresDAOImpl) daoP).consultarProfessor(rs.getString("nome_professor"));
                treino.setProfessor(professor);
                lista.add(treino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
	}
	

}
