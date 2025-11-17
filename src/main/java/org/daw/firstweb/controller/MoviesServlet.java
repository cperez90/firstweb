package org.daw.firstweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.daw.firstweb.dto.MovieDto;
import org.daw.firstweb.service.MovieService;
import org.daw.firstweb.service.MovieServiceImpl;

import java.io.IOException;

@WebServlet(name = "movieServlet", value = "/movies")
public class MoviesServlet extends HttpServlet {

    private MovieService service;

    @Override
    public void init(){
        service = new MovieServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");

        if (method != null) {
            if (method.equalsIgnoreCase("DELETE")) {
                doDelete(req, resp);
                return;
            }
            if (method.equalsIgnoreCase("PUT")) {
                doPut(req, resp);
                return;
            }
        }
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("text/html;charset=UTF-8");

        PrintWriter out = resp.getWriter();

        out.println("<h1>Hola som una Movie</h1>");*/

        /*String nom = req.getParameter("nom");

        req.setAttribute("nomPeli", "The Matrix");

        req.getRequestDispatcher("movie.js").forward(req,resp);*/

        /*service.addMovie(new Movie(1, "Inception", "A thief enters dreams to steal secrets."));
        service.addMovie(new Movie(2, "The Matrix", "A hacker discovers the truth about reality."));
        service.addMovie(new Movie(3, "Interstellar", "Explorers travel through a wormhole to save humanity."));
        service.addMovie(new Movie(4, "The Dark Knight", "Batman faces the Joker in Gotham City."));
        service.addMovie(new Movie(5, "Avatar", "A soldier becomes part of an alien world."));*/

        /*String nameParam = "rings";
        Connection conn = null;
        try {
            conn = JdbcConnector.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {

            PreparedStatement pst = conn.prepareStatement("select * from movies" where name like ? and rate > ?);
            //pst.setString(1,nameParam);
            //pst.setFloat(2,4);
            ResultSet result = pst.executeQuery();
            while (result.next()){
                int movieId = result.getInt("id");
                String movieTitle = result.getString("title");
                String movieDescription = result.getString("description");
                int movieYear = result.getInt("year");
                Movie movie = new Movie(movieId,movieTitle,movieDescription,movieYear,0);

                service.addMovie(movie);
            }
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e + " No hace la conexion");
        }*/

        HttpSession session = req.getSession(false);

        if (session != null){
            req.setAttribute("username",session.getAttribute("username"));
        }

        if (req.getParameter("id") == null) {
            req.setAttribute("movies", service.findAll());
            req.getRequestDispatcher("movies.jsp").forward(req, resp);
        } else {
            long id = Long.parseLong(req.getParameter("id"));
            if (req.getParameter("title") != null) {
                req.setAttribute("movie", service.findById(id));
                req.getRequestDispatcher("editMovie.jsp").forward(req, resp);
            }else {
                req.setAttribute("movie", service.findById(id));
                req.getRequestDispatcher("movie.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String movieName = req.getParameter("title");
            String movieDescription = req.getParameter("description");
            String movieYear = req.getParameter("year");

            if (movieName != null && movieDescription != null && movieYear != null) {
                MovieDto newMovie = new MovieDto(movieName, movieDescription, Integer.parseInt(movieYear));
                service.addMovie(newMovie);
            }
            resp.sendRedirect("movies");
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idParam = req.getParameter("id");

        if (idParam == null || idParam.isBlank()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Error: 'id'.");
            return;
        }

        try {
            Long id = Long.parseLong(idParam);
            service.deleteMovieById(id);

            resp.sendRedirect("movies");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        int year = Integer.parseInt(req.getParameter("year"));
        try {
            MovieDto movieDto = new MovieDto(id,title,description,year);
            service.updateMovie(movieDto);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.sendRedirect("movies?id=" + id);
        }catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error al actualizar la movie: " + e.getMessage());
        }
    }
}
