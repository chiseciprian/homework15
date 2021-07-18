export class InvoiceModel {

  constructor(
    public id: string,
    public description: string,
    public amount: number,
    public sender: string,
    public receiver: string,
    public payed: boolean
  ) {
  }
}
