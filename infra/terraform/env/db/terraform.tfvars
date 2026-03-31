project_name        = "rekruite"
resource_group_name = "rg-rekruite-prod"
location            = "Switzerland North"
db_server_name      = "mysql-rekruite-prod"
db_admin_login      = "rekruiteadmin"
db_name             = "rekruitedb"

tags = {
  environment = "production"
  project     = "rekruite"
  managed_by  = "terraform"
}
