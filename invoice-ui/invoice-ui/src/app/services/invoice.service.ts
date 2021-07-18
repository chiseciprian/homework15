import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {InvoiceRequest} from "../shared/request/invoice.request";

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  private configuration = 'http://localhost:9005';
  private endpoints = {
    getAllInvoices: () => this.configuration + `/invoices`,
    createInvoice: () => this.configuration + `/invoices`,
    deleteInvoice: (invoiceId: string) => this.configuration + `/invoices/` + invoiceId
  };

  constructor(private http: HttpClient) {
  }

  getAllInvoices(): Observable<any> {
    return this.http.get(this.endpoints.getAllInvoices(), httpOptions);
  }

  deleteInvoice(invoiceId: string): Observable<any> {
    return this.http.delete(this.endpoints.deleteInvoice(invoiceId), httpOptions);
  }

  createInvoice(invoiceRequest: InvoiceRequest): Observable<any> {
    return this.http.post(this.endpoints.createInvoice(), invoiceRequest, httpOptions);
  }
}
