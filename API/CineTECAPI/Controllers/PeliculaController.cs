using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using CineTECAPI.Models;

namespace CineTECAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PeliculaController : Controller
    {
        private readonly IConfiguration _configuration;

        public PeliculaController(IConfiguration configuration)
        {
            _configuration = configuration; //Inyeccion de dependencia
        }

        [HttpGet]
        public JsonResult Get() //Este procedimiento es necesario para que el rest api pueda conectarse a la base de datos postgresql
                                //y pueda desplegar los datos como json
        {
            //String de sql para recibir los datos de la tabla
            string query = @"                                   
                select nombreog as ""nombreog"",
                   nombre as ""nombre"",
                   imagen as ""imagen"",
                   duracion as ""duracion"",
                   protagonistas as ""protagonistas"",
                   director as ""director"",
                   clasificacion as ""clasificacion""
                from Pelicula
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon"); //Obtiene el string de postgres 
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource)) //Setea la configuracion de la conexion
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon)) //Lee los datos de la tabla 
                {
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult(table); //Devuelve los datos de la tabla en formato JSON

        }

        [HttpPost]
        public JsonResult Post(Pelicula pl)
        {
            string query = @"
                insert into Pelicula(nombreog, nombre, imagen, duracion, protagonistas, director, clasificacion) 
                values             (@nombreog, @nombre, @imagen, @duracion, @protagonistas, @director, @clasificacion)             
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@nombreog", pl.nombreog);
                    myCommand.Parameters.AddWithValue("@nombre", pl.nombre);
                    myCommand.Parameters.AddWithValue("@imagen", pl.imagen);
                    myCommand.Parameters.AddWithValue("@duracion", pl.duracion);
                    myCommand.Parameters.AddWithValue("@protagonistas", pl.protagonistas);
                    myCommand.Parameters.AddWithValue("@director", pl.director);
                    myCommand.Parameters.AddWithValue("@clasificacion", pl.clasificacion);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Pelicula añadida exitosamente");
        }


        [HttpPut]
        public JsonResult Put(Pelicula pl)

        {
            string query = @"
                update Pelicula 
                set nombre = @nombre,
                    imagen = @imagen,
                    duracion = @duracion,
                    protagonistas = @protagonistas,
                    director = @director,
                    clasificacion = @clasificacion
                where nombreog=@nombreog
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@nombreog", pl.nombreog);
                    myCommand.Parameters.AddWithValue("@nombre", pl.nombre);
                    myCommand.Parameters.AddWithValue("@imagen", pl.imagen);
                    myCommand.Parameters.AddWithValue("@duracion", pl.duracion);
                    myCommand.Parameters.AddWithValue("@protagonistas", pl.protagonistas);
                    myCommand.Parameters.AddWithValue("@director", pl.director);
                    myCommand.Parameters.AddWithValue("@clasificacion", pl.clasificacion);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Datos de pelicula actualizados exitosamente");
        }

        [HttpDelete("{id}")]
        public JsonResult Delete(string nombreog)

        {
            string query = @"
                delete from Pelicula
                where nombreog=@nombreog
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {

                    myCommand.Parameters.AddWithValue("@nombreog", nombreog);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Pelicula borrada exitosamente");
        }

    }
}