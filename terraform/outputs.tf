output "build_server_ip" {
  description = "Public IP address of the build server"
  value       = aws_instance.build_server.public_ip
}

output "deploy_server_ip" {
  description = "Public IP address of the deploy server"
  value       = aws_instance.deploy_server.public_ip
}