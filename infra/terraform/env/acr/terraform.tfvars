project_name        = "rekruite"
resource_group_name = "rg-rekruite-prod"
location            = "Switzerland North"
acr_name            = "acrrekruiteprod"

tags = {
  environment = "production"
  project     = "rekruite"
  managed_by  = "terraform"
}
