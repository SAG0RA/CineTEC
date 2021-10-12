using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Npgsql;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using CineTECAPI.Models;
using System.IO;
using OfficeOpenXml;
using System.Reflection;

namespace CineTECAPI.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SalaController : Controller
    {
        private readonly IConfiguration _configuration;

        public SalaController(IConfiguration configuration)
        {
            _configuration = configuration; //Inyeccion de dependencia
        }

        [HttpGet]
        public JsonResult Get() //Este procedimiento es necesario para que el rest api pueda conectarse a la base de datos postgresql
                                //y pueda desplegar los datos como json
        {
            //String de sql para recibir los datos de la tabla
            string query = @"                                   
                select id as ""id"",
                   sucursal as ""sucursal"",
                   filas as ""filas"",
                   columnas as ""columnas""
                from Sala
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
                select id as ""id"",
                   sucursal as ""sucursal"",
                   filas as ""filas"",
                   columnas as ""columnas""
                from Sala
                where id = @id
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon"); //Obtiene el string de postgres 
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource)) //Setea la configuracion de la conexion
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon)) //Lee los datos de la tabla 
                {
                    myCommand.Parameters.AddWithValue("@id", id);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult(table); //Devuelve los datos de la tabla en formato JSON
        }


        [HttpPost]
        public JsonResult Post(Sala sl)
        {
            string query = @"
                insert into Sala(id, sucursal, filas, columnas) 
                values          (@id, @sucursal, @filas, @columnas); 

                UPDATE Sucursal
	                SET cantidadsalas = cantidadsalas + 1
	                WHERE ubicacion = @currsucursal;
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@id", sl.id); //sustituir por los valores en el query 
                    myCommand.Parameters.AddWithValue("@sucursal", sl.sucursal);
                    myCommand.Parameters.AddWithValue("@filas", sl.filas);
                    myCommand.Parameters.AddWithValue("@columnas", sl.columnas);

                    myCommand.Parameters.AddWithValue("@currsucursal", sl.sucursal);

                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();

                }
            }
            //Creacion del excel para administrar los asientos de la sala
            var filxcol = (sl.filas * sl.columnas);
            //var nombresala = sl.tablaexcel;
            string directory = Directory.GetCurrentDirectory();
            var file = new FileInfo(@$"{directory}" + "\\Salas\\sala" + $"{sl.id}" + ".xlsx");
            SaveExcelFile(sl, filxcol, file);

            return new JsonResult("Sala" + $"{sl.id} Introducida exitosamente. Asientos de la sala: " + $"{filxcol}");
        }

        private void SaveExcelFile(Sala sl, object filxcol, FileInfo file)
        {
            DeleteIfExists(file);                                                                   //Borrar el archivo si existe
            ExcelPackage.LicenseContext = LicenseContext.NonCommercial;
            var filas = sl.filas;
            var columnas = sl.columnas; //el mae se cae si no hago esto asi
            var covid = false;
            
            //create a new ExcelPackage
            
            using (var package = new ExcelPackage(file))                          //usa un package que se desecha al salir del bloque de codigo
            {
                var ws = package.Workbook.Worksheets.Add("sala" + $"{sl.id}");     //crea un nuevo worksheet
       
                for (int i = 1; i <= filas; i++)
                {
                    for (int j = 1; j <= columnas; j++) //llenar las salas con el aforo covid
                    {
                        switch (covid)
                        {
                            case true:
                                ws.Cells[i, j].Value = "C"; //Covid
                                covid = false;
                                break;
                            case false:
                                ws.Cells[i, j].Value = "D"; //Disponible
                                covid = true; 
                                break; 
                        }
                    }
                }

                package.SaveAsync();//guarda el excel 
            }
        }

        private static void DeleteIfExists(FileInfo file)
        {
            if (file.Exists)
            {
                file.Delete();
            }
        }

        [HttpPut]
        public JsonResult Put(Sala sl)

        {
            string query = @"
                update Sala 
                set sucursal = @sucursal,
                    filas = @filas,
                    columnas = @columnas
                where id=@id
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {
                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {
                    myCommand.Parameters.AddWithValue("@id", sl.id);
                    myCommand.Parameters.AddWithValue("@sucursal", sl.sucursal);
                    myCommand.Parameters.AddWithValue("@filas", sl.filas);
                    myCommand.Parameters.AddWithValue("@columnas", sl.columnas);


                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            return new JsonResult("Datos de sala actualizados exitosamente");
        }

        [HttpDelete("{id}")]
        public JsonResult Delete(int id)

        {
            string query = @"
                UPDATE public.sucursal
	                SET cantidadsalas = cantidadsalas - 1
	                WHERE ubicacion = (select sucursal from sala
	                where id = @id);
	
	                delete from sala
	                where id = @id
            ";

            DataTable table = new DataTable();
            string sqlDataSource = _configuration.GetConnectionString("APIAppCon");
            NpgsqlDataReader myReader;
            using (NpgsqlConnection myCon = new NpgsqlConnection(sqlDataSource))
            {

                myCon.Open();
                using (NpgsqlCommand myCommand = new NpgsqlCommand(query, myCon))
                {

                    myCommand.Parameters.AddWithValue("@id", id);
                    myReader = myCommand.ExecuteReader();
                    table.Load(myReader);

                    myReader.Close();
                    myCon.Close();
                }
            }
            
            string directory = Directory.GetCurrentDirectory();
            var file = new FileInfo(@$"{directory}" + "\\Salas\\sala" + $"{id}" + ".xlsx");
            file.Delete();

            return new JsonResult("Sala borrada exitosamente");
        }

    }
}