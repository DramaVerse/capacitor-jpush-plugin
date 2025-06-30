export interface JPushPluginPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
