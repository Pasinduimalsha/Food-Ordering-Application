resource "aws_security_group" "build_server_sg" {
  name        = "build_server_sg"
  description = "Food Ordering Application Security Group"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "Build Server"
  }
}

resource "aws_instance" "build_server" {
  ami = var.ami
  subnet_id = data.aws_subnet.default.id
  vpc_security_group_ids = [aws_security_group.build_server_sg.id]
  instance_type = var.instance_type
  key_name = var.key_name
  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              yum install -y httpd
              systemctl start httpd
              systemctl enable httpd
              EOF
  tags = {
    Name = "Build Server"
  }
}

resource "aws_security_group" "deploy_server_sg" {
  name        = "deploy_server_sg"
  description = "Deployment Server Security Group"
  vpc_id      = data.aws_vpc.default.id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 22
    to_port     = 22
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "Deploy Server"
  }
}

resource "aws_instance" "deploy_server" {
  ami = var.ami
  subnet_id = data.aws_subnet.default.id
  vpc_security_group_ids = [aws_security_group.deploy_server_sg.id]
  instance_type = var.instance_type
  key_name = var.key_name
  user_data = <<-EOF
              #!/bin/bash
              yum update -y
              yum install -y docker
              systemctl start docker
              systemctl enable docker
              EOF
  tags = {
    Name = "Deploy Server"
  }
}
