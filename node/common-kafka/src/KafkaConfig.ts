import { Kafka } from 'kafkajs';
export default new Kafka({
    clientId: 'my-app',
    brokers: ['127.0.0.1:9092']
});