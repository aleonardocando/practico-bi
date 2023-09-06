import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit{

  public codePrincipal: any ={
    code:''
  }
  

  resul:any;
  employee:any;
  error: boolean | undefined;
  constructor(private http:HttpClient){ }

  ngOnInit(): void{
    
  }
  getInformation():void{
    if(this.codePrincipal.code.length==9){
      this.error = false;
      let res = this.http.get("http://localhost:8080/searcher?code="+this.codePrincipal.code)
      res.subscribe((data)=>this.resul=data)
      
      let res2 = this.http.get("http://localhost:8080/searcher/employee/?code="+this.codePrincipal.code)
      res2.subscribe((data2)=>this.employee=data2)
    }else{
      this.error = true;
      this.resul = undefined;
    }
  }

}
