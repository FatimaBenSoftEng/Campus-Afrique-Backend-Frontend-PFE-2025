import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class FormulaireSharedService {
  private formData: any = {};

  setFormData(data: any): void {
    this.formData = { ...data };
  }

  getFormData(): any {
    return this.formData;
  }

  clearData(): void {
    this.formData = {};
  }
}
