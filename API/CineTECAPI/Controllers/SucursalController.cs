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
    public class SucursalController : Controller
    {
        private readonly IConfiguration _configuration;

        public SucursalController(IConfiguration configuration)
        {
            _configuration = configuration; //Inyeccion de dependencia
        }

        [HttpGet]
        public JsonResult Get() //Este procedimiento es necesario para que el rest api pueda conectarse a la base de datos postgresql
                                //y pueda desplegar los datos como json
        {
            //String de sql para recibir los datos de la tabla
            string query = @"                                   
                select cineid as ""cineid"",
                   nombrecine as ""nombrecine"",
                   ubicacion as ""ubicacion"",
                   cantidadsalas as ""cantidadsalas""
                from Sucursal
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

        [HttpGet("{id}")]
        public JsonResult GetbyId(int id)
        {
            string query = @"                                   
                select cineid as ""cineid"",
                   nombrecine as ""nombrecine"",
                   ubicacion as ""ubicacion"",
                   cantidadsalas as ""cantidadsalas""
                from Sucursal
                where cineid = @cineid
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon"); //Obtiene el string de postgres 
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource)) //Setea la configuracion de la conexion
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon)) //Lee los datos de la tabla 
                {
                    myCommand.Parameters.AddWithValue("@cineid", id);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult(table); //Devuelve los datos de la tabla en formato JSON
        }

        [HttpPost]
        public JsonResult Post(Sucursal scl)
        {
            string query = @"
                insert into Sucursal(cineid, nombrecine, ubicacion, cantidadsalas) 
                values              (@cineid, @nombrecine, @ubicacion, @cantidadsalas)            
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@cineid", scl.cineid);
                    myCommand.Parameters.AddWithValue("@nombrecine", scl.nombrecine);
                    myCommand.Parameters.AddWithValue("@ubicacion", scl.ubicacion);
                    myCommand.Parameters.AddWithValue("@cantidadsalas", scl.cantidadsalas);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Sucursal añadida exitosamente");
        }


        [HttpPut]
        public JsonResult Put(Sucursal scl)

        {
            string query = @"
                update Sucursal 
                set cineid = @cineid, 
                    nombrecine = @nombrecine,
                    ubicacion = @ubicacion,
                    cantidadsalas = @cantidadsalas
                where cineid = @cineid
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@cineid", scl.cineid);
                    myCommand.Parameters.AddWithValue("@nombrecine", scl.nombrecine);
                    myCommand.Parameters.AddWithValue("@ubicacion", scl.ubicacion);
                    myCommand.Parameters.AddWithValue("@cantidadsalas", scl.cantidadsalas);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Datos de sucursal actualizados exitosamente");
        }

        [HttpDelete("{id}")]
        public JsonResult Delete(int id)

        {
            string query = @"
                delete from Sucursal
                where cineid=@cineid
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {

                    myCommand.Parameters.AddWithValue("@cineid", id);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Cine borrado exitosamente");
        }

    }
}