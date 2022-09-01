{{/*
Expand the name of the chart.
*/}}
{{- define "springboot.name" -}}
{{- default .Chart.Name .Values.nameOverride | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Create a default fully qualified app name.
We truncate at 63 chars because some Kubernetes name fields are limited to this (by the DNS naming spec).
If release name contains chart name it will be used as a full name.
*/}}
{{- define "springboot.fullname" -}}
{{- if .Values.fullnameOverride }}
{{- .Values.fullnameOverride | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- $name := default .Chart.Name .Values.nameOverride }}
{{- if contains $name .Release.Name }}
{{- .Release.Name | trunc 63 | trimSuffix "-" }}
{{- else }}
{{- printf "%s-%s" .Release.Name $name | trunc 63 | trimSuffix "-" }}
{{- end }}
{{- end }}
{{- end }}

{{/*
Create chart name and version as used by the chart label.
*/}}
{{- define "springboot.chart" -}}
{{- printf "%s-%s" .Chart.Name .Chart.Version | replace "+" "_" | trunc 63 | trimSuffix "-" }}
{{- end }}

{{/*
Common labels
*/}}
{{- define "springboot.labels" -}}
helm.sh/chart: {{ include "springboot.chart" . }}
{{ include "springboot.selectorLabels" . }}
{{- if .Chart.AppVersion }}
app.kubernetes.io/version: {{ .Chart.AppVersion | quote }}
{{- end }}
app.kubernetes.io/managed-by: {{ .Release.Service }}
{{- end }}

{{/*
Selector labels
*/}}
{{- define "springboot.selectorLabels" -}}
app.kubernetes.io/name: {{ include "springboot.name" . }}
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end }}

{{/*
Create the name of the service account to use
*/}}
{{- define "springboot.serviceAccountName" -}}
{{- if .Values.serviceAccount.create }}
{{- default (include "springboot.fullname" .) .Values.serviceAccount.name }}
{{- else }}
{{- default "default" .Values.serviceAccount.name }}
{{- end }}
{{- end }}


{{/*
Return the postgres Hostname
*/}}
{{- define "springboot.databaseHost" -}}
{{- if .Values.postgres.enabled }}
    {{- printf "%s" (include "springboot.postgres.fullname" .) -}}
{{- else -}}
    {{- printf "%s" .Values.db.host -}}
{{- end -}}
{{- end -}}

{{/*
Return the postgres Port
*/}}
{{- define "springboot.databasePort" -}}
{{- if .Values.postgres.enabled }}
    {{- printf "5432" -}}
{{- else -}}
    {{- printf "%d" (.Values.db.port | int ) -}}
{{- end -}}
{{- end -}}

{{/*
Return the postgres Database Name
*/}}
{{- define "springboot.databaseName" -}}
{{- if .Values.postgres.enabled }}
    {{- printf "%s" .Values.postgres.database -}}
{{- else -}}
    {{- printf "%s" .Values.db.name -}}
{{- end -}}
{{- end -}}

{{/*
Return the postgres User
*/}}
{{- define "springboot.databaseUser" -}}
{{- if .Values.postgres.enabled }}
    {{- printf "%s" .Values.postgres.dbuser -}}
{{- else -}}
    {{- printf "%s" .Values.db.user -}}
{{- end -}}
{{- end -}}

{{/*
Return the postgres Secret Name
*/}}
{{- define "springboot.databaseSecretName" -}}
{{- if .Values.postgres.enabled }}
    {{- printf "%s" (include "springboot.postgres.fullname" .) -}}
{{- else -}}
    {{- printf "%s-externaldb" (include "common.names.fullname" .) -}}
{{- end -}}
{{- end -}}

{{- define "kafka.fullname" -}}
{{- printf "%s-%s" .Release.Name "kafka" | trunc 63 | trimSuffix "-" }}
{{- end -}}

{{- define "postgres.fullname" -}}
{{- printf "%s-%s" .Release.Name "postgres" | trunc 63 | trimSuffix "-" }}
{{- end -}}

{{- define "postgres.url" -}}
{{- printf "jdbc:postgresql://%s:5432/%s" (include "postgres.fullname" .) "postgres" }}
{{- end -}}