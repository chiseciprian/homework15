export class InvoiceRequest {

  constructor(
    public description: string,
    public amount: number,
    public sender: string,
    public receiver: string
  ) {
  }
}
