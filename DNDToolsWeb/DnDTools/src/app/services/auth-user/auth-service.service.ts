import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private signedInUser = new BehaviorSubject<String>("");
  data = this.signedInUser.asObservable()

  constructor() { }

  sendData(data:String){
    this.signedInUser.next(data)
  }
  deleteData(){
    this.signedInUser.next("")
  }
}
