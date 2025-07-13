import { Component,NgZone,OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
declare var window: any;


@Component({
  selector: 'app-auth-hub',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './auth-hub.html',
  styleUrl: './auth-hub.css'
})

@Injectable({
  providedIn: 'root'
})
export class AuthHub implements OnInit {
  tableData: any[] = [];
  searchTerm: string = '';
  searchColumn: string = 'ID';
  showTable: boolean = true;
  showForm: boolean = false;
  isUpdateMode: boolean = false;
  showFormDel: boolean = false;
  deleteId: number | null = null;

  // Form fields
  authField1 = '';
  authField2 = '';
  authField3 = '';
  authField4 = '';
  authField5 = '';
  authField6 = '';
  authField7 = '';
  authField8 = '';
  authField9 = '';
  authField10 = '';
  authField11 = '';
  authField12 = '';

  

  constructor(private http: HttpClient, private ngZone: NgZone) {}
  
  private apiUrl = window.__env.apiUrl;

  
  ngOnInit(): void {
    this.loadTable();
  }

  loadTable() {
  this.http.get<any[]>(`${this.apiUrl}/authhub`).subscribe({
    next: (data) => {
      this.tableData = data || [];
    },
    error: (err) => {
      console.error('Failed to load initial data', err);
    }
  });
}

  searchData() {
    if (!this.searchTerm) {
      this.loadTable();
      return;
    }

    this.http
  .get<any[]>(`${this.apiUrl}/authhub/search/${this.searchColumn}/${this.searchTerm}`)
      .subscribe({
        next: (res) => (this.tableData = res),
        error: () => (this.tableData = [])
      });
  }

  clearSearch() {
    this.searchTerm = '';
    this.loadTable();
  }

  showAddForm() {
    this.clearForm();
    this.isUpdateMode = false;
    this.showForm = true;
    this.showFormDel = false;
    this.showTable = false;
  }


  showUpdateForm() {
    this.clearForm();
    this.isUpdateMode = true;
    this.showForm = true;
    this.showFormDel = false;
    this.showTable = false;
  }

  showDeleteForm() {
    this.clearForm();
    this.showFormDel = true;
    this.showTable = false;
  }


  backToTable(){
    this.clearForm();
    this.showForm = false;
    this.showFormDel =false;
    this.showTable = true;
  }

  clearForm() {
    this.authField1 = '';
    this.authField2 = '';
    this.authField3 = '';
    this.authField4 = '';
    this.authField5 = '';
    this.authField6 = '';
    this.authField7 = '';
    this.authField8 = '';
    this.authField9 = '';
    this.authField10 = '';
    this.authField11 = '';
    this.authField12 = '';
  }

  submitData() {
    const payload = {
      authField1: this.authField1,
      authField2: this.authField2 || null,
      authField3: this.authField3 || null,
      authField4: this.authField4 || null,
      authField5: this.authField5 || null,
      authField6: this.authField6 || null,
      authField7: this.authField7 === '' ? null : +this.authField7,
      authField8: this.authField8 || null,
      authField9: this.authField9 === '' ? 0 : +this.authField9,
      authField10: this.authField10 || null,
      authField11: this.authField11 === '' ? 0 : +this.authField11,
      authField12: this.authField12 || null
    };
    // from here 
    if (isNaN(+this.authField7)) {
    alert("CLIENT_CONFIG_ID must be a valid number.");
    return;
  }

  if (this.authField9 !== '0' && this.authField9 !== '1') {
    alert("IS_MULTI_USER must be 0 or 1.");
    return;
  }

  if (this.authField11 !== '0' && this.authField11 !== '1') {
    alert("IP_WHITELIST_FLAG must be 0 or 1.");
    return;
  }
  // till here 08-06-2025 23.57
  const url = this.isUpdateMode
  ? `${this.apiUrl}/authhub/${this.authField1}`
  : `${this.apiUrl}/authhub`;

    const method = this.isUpdateMode ? this.http.put : this.http.post;

    method.call(this.http, url, payload).subscribe({
      next: () => {
        alert(this.isUpdateMode ? 'Data updated' : 'Data inserted');
        this.loadTable();
        this.showForm = false;
      },
      error: (err) => {
        console.error('Submit error:', err);
        alert('Submit failed');
      }
    });
  }
deleteData() {
  if (!this.deleteId) {
    alert('Please enter a valid ID to delete.');
    return;
  }

  this.http.delete<any>(`${this.apiUrl}/authhub/delete/${this.deleteId}`)

    .subscribe({
      next: (res) => {
        alert(res.message);
        this.loadTable(); // Refresh table
        this.deleteId = 0;   // Clear input
      },
      error: (err) => {
        if (err.status === 404) {
          alert(err.error.message);
        } else {
          alert('Error deleting record.');
        }
      }
    });
}


}