import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import { MatTableDataSource } from '@angular/material/table';



@Component({
  
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  displayedColumnsBook: string[] = ['id', 'title', 'releaseyr'];
  displayedColumnsWriter: string[] = ['id', 'name', 'birth'];
  results:any;



  response: any;
  addBookTitle:any;
  addWriterName:any;
  delBookTitle: any;
  delWriterName: any;
  getBookAttributes: any;
  title: "Library-Frontend" | undefined;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void{
    this.onListingWriters();
  }
  onListingBooks(){
    let url = 'http://localhost:8888/api/book/list';
    this.http.get(url).subscribe((response) => {
      this.response = response;
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });
  }
  onListingWriters(){
    let url = 'http://localhost:8888/api/writer/list';
    this.http.get(url).subscribe((response) => {
      this.response = response;
      console.log(this.response);
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });

  }





  addBook() {
    ///add/{title}/{releaseyr}/{writerid}
    //id,writerid,title,releaseyr
    ///add/{title}/{releaseyr}/{writerid}/{id}")
    let url = 'http://localhost:8888/api/book/add/';
    console.log(this.addBookTitle);
    let addBookTitle = this.addBookTitle.split(",");
    this.http.post(url + addBookTitle[2] + "/" + addBookTitle[3] + "/" + addBookTitle[1]+"/"+addBookTitle[0], null).subscribe((response) => {
      this.response = response;
      console.log(this.response);
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });
  }
  addWriter() {
    ///add/{name}/{birth}
    //id,name,birth
    ///add/{name}/{birth}/{id}
    let url = 'http://localhost:8888/api/writer/add/';
    console.log(this.addWriterName);
    let addWriterName = this.addWriterName.split(",");
    console.log(addWriterName[1]+"/"+addWriterName[2]+"/"+addWriterName[0]);
    this.http.post(url + addWriterName[1] + "/" + addWriterName[2]+"/"+addWriterName[0], null).subscribe((response) => {
      this.response = response;
      console.log(this.response);
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });
  }
  deleteBook() {
    let url = 'http://localhost:8888/api/book/delete/';
    console.log(this.delBookTitle);
    this.http.delete(url + this.delBookTitle).subscribe((response) => {
      this.response = response;
      console.log(this.response);
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });
  }
  deleteWriter() {
    let url = 'http://localhost:8888/api/writer/delete/';
    console.log(this.delWriterName);
    this.http.delete(url + this.delWriterName).subscribe((response) => {
      this.response = response;
      console.log(this.response);
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });
  }

  getBook() {
    let url = 'http://localhost:8888/api/book/find/';
    this.http.get(url + this.getBookAttributes).subscribe((response) => {
      this.response = response;
      console.log(this.response);
    },
      (error:HttpErrorResponse) => {
        console.log(error);
      });
  }
}

