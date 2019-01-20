import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginAddminComponent } from './login-addmin/login-addmin.component';
import { MainAdminComponent } from './main-admin/main-admin.component';
import { ContractComponent } from './contract/contract.component';
const routes: Routes = [
  {path:'' , component:LoginAddminComponent},
  {path:'mainAdmin' , component:MainAdminComponent},
  {path:'contract' , component:ContractComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
